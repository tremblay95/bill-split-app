package ca.tremblay95.billsplit.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "split_method",
    indices = [Index(value = ["name"], unique = true)]
)
data class SplitMethod(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("method_id")
    val methodId : Int = 0,

    @ColumnInfo("name")
    val name : String,

    @ColumnInfo("description")
    val description : String
)
