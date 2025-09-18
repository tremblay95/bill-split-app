package ca.tremblay95.billsplit.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ca.tremblay95.billsplit.ui.model.SplitType

@Entity(
    tableName = "split_operation",
    foreignKeys = [
        ForeignKey(
            entity = SplitMethod::class,
            parentColumns = arrayOf("method_id"),
            childColumns = arrayOf("method_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
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

    @ColumnInfo("method_id")
    val methodId : Int,

    @ColumnInfo("parent_id")
    val parentId : Int?,

    @ColumnInfo("split_type")
    val splitType : SplitType,
)
