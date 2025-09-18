package ca.tremblay95.billsplit.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "split_operand",
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
data class SplitOperand(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("operand_id")
    val operandId : Int = 0,

    @ColumnInfo("value")
    val value : Double,

    @ColumnInfo("name")
    val name : String,

    @ColumnInfo("index")
    val index : Int,

    @ColumnInfo("operation_id")
    val operationId : Int
)