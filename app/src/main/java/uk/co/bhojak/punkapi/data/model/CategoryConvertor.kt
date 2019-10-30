package uk.co.bhojak.punkapi.data.model

import androidx.room.TypeConverter

class CategoryConverter {

    @TypeConverter
    fun toCategories(value: String?): List<String> {
        if (value == null || value.isEmpty()) {
            return ArrayList<String>()
        }

        val list: List<String> = value.split(",")
        val longList = ArrayList<String>()
        for (item in list) {
            if (!item.isEmpty()) {
                longList.add(item)
            }
        }
        return list
    }

    @TypeConverter
    fun toString(categories: List<String>): String {

        var string = ""

        if (categories == null) {
            return string
        }

        categories.forEach {
            string += "$it,"
        }
        return string
    }
}
