package com.mesutemre.namazvakitleri.core

object Constants {

    object DataStoreConstants {
        val DATASTORE_FILE = "namazvakitleri_datastore"
        val CITY_LIST_KEY = "citylist"
        val VAKIT_EXIST_KEY = "vakitinfo"
        val SELECTED_DISTRICT = "selecteddistrict"
        val TARIHTE_BUGUN_KEY = "tarihtebugunkey"
        val VAKIT_TYPE_PAGE = "vakittypepage"
    }

    object DashboardConstants {
        val SEC_MIL_SEC: Long = 1000
        val MIN_MIL_SEC = 60 * SEC_MIL_SEC
        val HOUR_MIL_SEC = 60 * MIN_MIL_SEC
        val DAY_MIL_SEC = 24 * HOUR_MIL_SEC
    }
}