package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.ui.model.FractionalSplitOperation
import ca.tremblay95.billsplit.ui.model.SplitMethod
import ca.tremblay95.billsplit.ui.model.SplitOperand

// TODO: Remove this. Temporary, for development purposes only
object DevelopmentLocalDataProvider {
    val splitMethods : List<SplitMethod>

    init {
        val utilitySplit = FractionalSplitOperation(listOf(
            SplitOperand(0.7, "Main Floor"),
            SplitOperand(0.3, "Basement")
        ))
        val mainFloorSplit = FractionalSplitOperation(listOf(
            SplitOperand(1.0/3.0, "Roommate A"),
            SplitOperand(1.0/3.0, "Roommate B"),
            SplitOperand(1.0/3.0, "Roommate C")
        ))

        utilitySplit.splitFurther(0, mainFloorSplit)

        splitMethods = listOf(
            SplitMethod(
                0,
                "Utilities Split",
                "Split the utility between the main floor and basement tenants, the split the main floor portion equally between the number of residents",
                utilitySplit,
            ),
            SplitMethod(1, "Empty Split 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethod(2, "Empty Split 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethod(3, "Empty Split 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethod(4, "Empty Split 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethod(5, "Empty Split 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethod(6, "Empty Split 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitMethod(7, "Empty Split 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
        )
    }
}