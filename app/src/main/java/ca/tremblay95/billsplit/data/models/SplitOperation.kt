package ca.tremblay95.billsplit.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import ca.tremblay95.billsplit.ui.model.SplitType

@Entity(
    tableName = "split_operation",
    indices = [Index(value = ["parent_id"])],
    foreignKeys = [
        ForeignKey(
            entity = SplitOperation::class,
            parentColumns = arrayOf("operation_id"),
            childColumns = arrayOf("parent_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SplitOperation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("operation_id")
    val operationId : Int = 0,

    @ColumnInfo("parent_id")
    val parentId : Int?,

    @ColumnInfo("split_type")
    val splitType : SplitType,

    @ColumnInfo("parent_operation_index")
    val parentOperationIndex : Int?
)
