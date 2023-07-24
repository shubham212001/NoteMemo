package com.sharma.notememo

import com.google.firebase.firestore.DocumentId

data class FirestoreTask(
    var title: String = "",
    var description: String = "",
    var date: String = "",
    var time: String = ""
) {
    @DocumentId
    var id: String = ""
}
