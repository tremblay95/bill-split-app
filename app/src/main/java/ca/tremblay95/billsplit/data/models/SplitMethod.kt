package ca.tremblay95.billsplit.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "split_method",
    indices = [
        Index(value = ["name"], unique = true),
        Index(value = ["operation_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = SplitOperation::class,
            parentColumns = arrayOf("operation_id"),
            childColumns = arrayOf("operation_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SplitMethod(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("method_id")
    val methodId : Int = 0,

    @ColumnInfo("name")
    val name : String,

    @ColumnInfo("description")
    val description : String,

    @ColumnInfo("operation_id")
    val operationId : Int? = null
)
