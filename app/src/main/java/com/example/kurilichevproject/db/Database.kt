package com.example.kurilichevproject.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Landmarks: IntIdTable() {
    val title = varchar("title", 255)
    val address = varchar("address", 255)
    val shortDescription = varchar("shortDescription", 1000)
    val detaileDescription = varchar("detaileDescription", 1000)
    val isFavorite = bool("isFavorite")
    val comment = text("comment")
}
object LandmarkImages: IntIdTable() {
    val landmark = reference("landmark", Landmarks)
    val image = text("image")
}
class LandmarkImage(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<LandmarkImage>(LandmarkImages)
    var landmark by Landmark referencedOn LandmarkImages.landmark
    var image by LandmarkImages.image
}
class Landmark(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Landmark>(Landmarks)
    var title by Landmarks.title
    var address by Landmarks.address
    var shortDescription by Landmarks.shortDescription
    var detaileDescription by Landmarks.detaileDescription
    var isFavorite by Landmarks.isFavorite
    var comment by Landmarks.comment

    val images by LandmarkImage referrersOn LandmarkImages.landmark
}


