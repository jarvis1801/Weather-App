package com.jarvis.weatherapp.dao

import com.google.gson.Gson
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.util.Extension.fromJson

class DaoTestData {
    companion object {
        fun createDummyWeather() : List<WeatherResponse> {
            val dummyList = arrayListOf<WeatherResponse>()
            val dummy1 = Gson().fromJson<WeatherResponse>(dummy1Json).apply {
                updatedAt = System.currentTimeMillis()
            }
            val dummy2 = Gson().fromJson<WeatherResponse>(dummy2Json).apply {
                updatedAt = System.currentTimeMillis()
            }
            dummyList.add(dummy1)
            dummyList.add(dummy2)
            return dummyList
        }


        val dummy1Json = "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 114,\n" +
                "        \"lat\": 22\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 804,\n" +
                "            \"main\": \"Clouds\",\n" +
                "            \"description\": \"overcast clouds\",\n" +
                "            \"icon\": \"04d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 303.75,\n" +
                "        \"feels_like\": 310.75,\n" +
                "        \"temp_min\": 300.92,\n" +
                "        \"temp_max\": 304.2,\n" +
                "        \"pressure\": 1004,\n" +
                "        \"humidity\": 87,\n" +
                "        \"sea_level\": 1004,\n" +
                "        \"grnd_level\": 1004\n" +
                "    },\n" +
                "    \"visibility\": 10000,\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 8.04,\n" +
                "        \"deg\": 196,\n" +
                "        \"gust\": 11.92\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 100\n" +
                "    },\n" +
                "    \"dt\": 1654334505,\n" +
                "    \"sys\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": 9154,\n" +
                "        \"country\": \"HK\",\n" +
                "        \"sunrise\": 1654292409,\n" +
                "        \"sunset\": 1654340680\n" +
                "    },\n" +
                "    \"timezone\": 28800,\n" +
                "    \"id\": 1818775,\n" +
                "    \"name\": \"Sok Kwu Wan\",\n" +
                "    \"cod\": 200\n" +
                "}"

        val dummy2Json = "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 114.1577,\n" +
                "        \"lat\": 22.2855\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 804,\n" +
                "            \"main\": \"Clouds\",\n" +
                "            \"description\": \"overcast clouds\",\n" +
                "            \"icon\": \"04d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 303.05,\n" +
                "        \"feels_like\": 310.05,\n" +
                "        \"temp_min\": 300.9,\n" +
                "        \"temp_max\": 305.47,\n" +
                "        \"pressure\": 1004,\n" +
                "        \"humidity\": 79\n" +
                "    },\n" +
                "    \"visibility\": 10000,\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 6.71,\n" +
                "        \"deg\": 220\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 100\n" +
                "    },\n" +
                "    \"dt\": 1654334521,\n" +
                "    \"sys\": {\n" +
                "        \"type\": 2,\n" +
                "        \"id\": 265999,\n" +
                "        \"country\": \"HK\",\n" +
                "        \"sunrise\": 1654292337,\n" +
                "        \"sunset\": 1654340676\n" +
                "    },\n" +
                "    \"timezone\": 28800,\n" +
                "    \"id\": 1819729,\n" +
                "    \"name\": \"Hong Kong\",\n" +
                "    \"cod\": 200\n" +
                "}"
    }
}