package com.example.myappbankuish.model

import android.os.Parcel
import android.os.Parcelable

data class GitRepositories(
    val owner: Owner?,
    val name: String?,
    val full_name:String?,
    val description:String?,
    val html_url:String?,
    val created_at:String?,
    val updated_at:String?,
    val pushed_at:String?,
    val clone_url:String?,
    val visibility:String?,
    val language:String?,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Owner::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(owner, flags)
        parcel.writeString(name)
        parcel.writeString(full_name)
        parcel.writeString(description)
        parcel.writeString(html_url)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeString(pushed_at)
        parcel.writeString(clone_url)
        parcel.writeString(visibility)
        parcel.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitRepositories> {
        override fun createFromParcel(parcel: Parcel): GitRepositories {
            return GitRepositories(parcel)
        }

        override fun newArray(size: Int): Array<GitRepositories?> {
            return arrayOfNulls(size)
        }
    }
}


data class ListGitRepositories(val items: List<GitRepositories>?):Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(GitRepositories)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListGitRepositories> {
        override fun createFromParcel(parcel: Parcel): ListGitRepositories {
            return ListGitRepositories(parcel)
        }

        override fun newArray(size: Int): Array<ListGitRepositories?> {
            return arrayOfNulls(size)
        }
    }
}
