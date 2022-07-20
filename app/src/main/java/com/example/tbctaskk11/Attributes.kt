package com.example.tbctaskk11

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attributes(
    val image: Int?,
    var description: String
):Parcelable

var attributesList = mutableListOf(
    Attributes(
        R.drawable.vacation,
        "us on vacation"
    ),

    Attributes(
        null,
        "შემთხვევითად გენერირებული ტექსტი ეხმარება დიზაინერებს და ტიპოგრაფიული ნაწარმის შემქმნელებს, რეალურთან მაქსიმალურად მიახლოებული შაბლონი წარუდგინონ შემფასებელს."
    ),

    Attributes(
        R.drawable.funeral,
        "us on funeral"
    ),
    Attributes(
        R.drawable.birthday,
        "us on birthday"
    ),

    Attributes(
            null,
    "შემთხვევითად გენერირებული ტექსტი ეხმარება დიზაინერებს და ტიპოგრაფიული ნაწარმის შემქმნელებს, რეალურთან მაქსიმალურად მიახლოებული შაბლონი წარუდგინონ შემფასებელს."
    )

)