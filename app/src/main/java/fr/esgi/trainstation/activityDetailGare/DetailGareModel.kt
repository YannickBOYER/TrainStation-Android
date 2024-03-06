package fr.esgi.trainstation.activityDetailGare

import android.os.Parcel
import android.os.Parcelable

class DetailGareModel() : Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailGareModel> {
        override fun createFromParcel(parcel: Parcel): DetailGareModel {
            return DetailGareModel(parcel)
        }

        override fun newArray(size: Int): Array<DetailGareModel?> {
            return arrayOfNulls(size)
        }
    }
}