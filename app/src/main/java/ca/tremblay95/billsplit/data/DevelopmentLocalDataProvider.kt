package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.ui.model.FractionalSplitOperation
import ca.tremblay95.billsplit.ui.model.SplitMethodDetails
import ca.tremblay95.billsplit.ui.model.SplitOperandDetails

// TODO: Remove this. Temporary, for development purposes only
object DevelopmentLocalDataProvider {
    val methodDetails : List<SplitMethodDetails>

    init {
        val utilitySplit = FractionalSplitOperation(fractions = listOf(
            SplitOperandDetails(0.7, "Main Floor"),
            SplitOperandDetails(0.3, "Basement")
        ))
        val mainFloorSplit = FractionalSplitOperation(fractions = listOf(
            SplitOperandDetails(1.0/3.0, "Roommate A"),
            SplitOperandDetails(1.0/3.0, "Roommate B"),
            SplitOperandDetails(1.0/3.0, "Roommate C")
        ))

        utilitySplit.splitFurther(0, mainFloorSplit)

        methodDetails = listOf(
            SplitMethodDetails(
                0,
                "Utilities Split",
                "Split the utility between the main floor and basement tenants, the split the main floor portion equally between the number of residents",
                utilitySplit,
            ),
            SplitMethodDetails(1, "Empty Split 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethodDetails(2, "Empty Split 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethodDetails(3, "Empty Split 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethodDetails(4, "Empty Split 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethodDetails(5, "Empty Split 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethodDetails(6, "Empty Split 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethodDetails(7, "Empty Split 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
        )
    }
}
