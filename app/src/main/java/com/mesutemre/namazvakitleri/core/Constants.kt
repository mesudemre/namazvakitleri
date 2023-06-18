package com.mesutemre.namazvakitleri.core

object Constants {

    object DataStoreConstants {
        val DATASTORE_FILE = "namazvakitleri_datastore"
        val CITY_LIST_KEY = "citylist"
        val VAKIT_EXIST_KEY = "vakitinfo"
        val SELECTED_DISTRICT = "selecteddistrict"
        val TARIHTE_BUGUN_KEY = "tarihtebugunkey2"
        val VAKIT_TYPE_PAGE = "vakittypepage"
        val PUSH_TOKEN = "pushtoken"
    }

    object DashboardConstants {
        val SEC_MIL_SEC: Long = 1000
        val MIN_MIL_SEC = 60 * SEC_MIL_SEC
        val HOUR_MIL_SEC = 60 * MIN_MIL_SEC
        val DAY_MIL_SEC = 24 * HOUR_MIL_SEC
    }

    object FirebaseConstants {
        val DB_INSTANCE_URL = "https://namazvakitleri-b182f-default-rtdb.europe-west1.firebasedatabase.app/"
        val PUSH_TOKEN_DB = "push_notification"
        val PUSH_TOKEN_DB_ORDERBY_CHILD = "token"
    }

    object ChannelConstants {
        val VAKIT_PUSH_CHANNEL_ID = "vakit_push_channel_id"
        val VAKIT_PUSH_CHANNEL_NAME = "vakit_push_channel"
        val CUMA_HATIRLATICI_CHANNEL_ID = "cuma_hatirlatici_channel_id"
        val CUMA_HATIRLATICI_CHANNEL_NAME = "cuma_hatirlatici_channel"
    }
}