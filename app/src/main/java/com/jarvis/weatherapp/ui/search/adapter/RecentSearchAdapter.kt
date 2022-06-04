package com.jarvis.weatherapp.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.weatherapp.R
import com.jarvis.weatherapp.databinding.ItemRecentSearchBinding
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.model.WeatherResponse.Companion.TYPE_LOCATION
import java.lang.ref.WeakReference

class RecentSearchAdapter(
    var onClick: (weatherResponse: WeatherResponse, position: Int) -> Unit,
    var onDeleteClick: (weatherResponse: WeatherResponse, position: Int) -> Unit,
) : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {

    private lateinit var context: WeakReference<Context>
    private var dataList = arrayListOf<WeatherResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = WeakReference(parent.context)
        return ViewHolder(ItemRecentSearchBinding.inflate(LayoutInflater.from(context.get()), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    override fun getItemCount(): Int = dataList.size

    fun updateData(newData: ArrayList<WeatherResponse>) {
        dataList = newData
        notifyDataSetChanged()
    }

    fun removePosition(position: Int) {
        dataList.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRecentSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherResponse: WeatherResponse, position: Int) {
            val context = context.get()
            if (weatherResponse.type == TYPE_LOCATION) {
                binding.tvRecentSearchOrLocation.text = "Your location"
                context?.let { binding.ivIcon.setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_my_location_48, null)) }
                binding.ivDelete.isVisible = false
            } else {
                binding.tvRecentSearchOrLocation.text = weatherResponse.name
                context?.let { binding.ivIcon.setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_location_on_48, null)) }
                binding.ivDelete.isVisible = true
            }
            binding.root.setOnClickListener {
                onClick(weatherResponse, position)
            }

            binding.ivDelete.setOnClickListener {
                removePosition(position)
                onDeleteClick(weatherResponse, position)
            }
        }
    }
}