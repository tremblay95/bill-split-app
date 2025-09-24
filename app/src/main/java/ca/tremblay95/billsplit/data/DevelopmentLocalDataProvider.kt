package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.domain.model.DivideOperation
import ca.tremblay95.billsplit.domain.model.Operand
import ca.tremblay95.billsplit.domain.model.Split

// TODO: Remove this. Temporary, for development purposes only
object DevelopmentLocalDataProvider {
    val splits : List<Split>

    init {
        val utilitySplit = DivideOperation(listOf(
            Operand(0.7, "Main Floor"),
            Operand(0.3, "Basement")
        ))
        val mainFloorSplit = DivideOperation(listOf(
            Operand(1.0/3.0, "Roommate A"),
            Operand(1.0/3.0, "Roommate B"),
            Operand(1.0/3.0, "Roommate C")
        ))

        utilitySplit.splitFurther(0, mainFloorSplit)

        splits = listOf(
            Split(
                0,
                "Utilities Split",
                "Split the utility between the main floor and basement tenants, the split the main floor portion equally between the number of residents",
                utilitySplit,
            ),
            Split(1, "Empty Split 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            Split(2, "Empty Split 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            Split(3, "Empty Split 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            Split(4, "Empty Split 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            Split(5, "Empty Split 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            Split(6, "Empty Split 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            Split(7, "Empty Split 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
        )
    }
}
