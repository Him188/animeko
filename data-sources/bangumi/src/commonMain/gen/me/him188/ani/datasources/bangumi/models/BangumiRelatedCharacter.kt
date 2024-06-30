/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport",
)

package me.him188.ani.datasources.bangumi.models

import me.him188.ani.datasources.bangumi.models.BangumiCharacterType
import me.him188.ani.datasources.bangumi.models.BangumiPerson
import me.him188.ani.datasources.bangumi.models.BangumiPersonImages

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 *
 *
 * @param id
 * @param name
 * @param type 角色，机体，舰船，组织...
 * @param relation
 * @param images object with some size of images, this object maybe `null`
 * @param actors 演员列表
 */
@Serializable

data class BangumiRelatedCharacter(

    @SerialName(value = "id") @Required val id: kotlin.Int,

    @SerialName(value = "name") @Required val name: kotlin.String,

    /* 角色，机体，舰船，组织... */
    @SerialName(value = "type") @Required val type: BangumiCharacterType,

    @SerialName(value = "relation") @Required val relation: kotlin.String,

    /* object with some size of images, this object maybe `null` */
    @SerialName(value = "images") val images: BangumiPersonImages? = null,

    /* 演员列表 */
    @SerialName(value = "actors") val actors: kotlin.collections.List<BangumiPerson>? = arrayListOf()

)
