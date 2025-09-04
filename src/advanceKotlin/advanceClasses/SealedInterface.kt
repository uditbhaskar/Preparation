package advanceKotlin.advanceClasses

/**
 * # Sealed Interface Example
 * 
 * This file demonstrates a simple sealed class hierarchy for UI events.
 * Shows how object declarations can extend sealed classes.
 * 
 * ## Topics Covered:
 * - **Sealed Class**: Restricted class hierarchy
 * - **Object Declaration**: Singleton object extending sealed class
 * - **UI Event Pattern**: Common pattern in Android development
 * 
 * @author Udit
 * @since 1.0
 */

sealed class UiEvent

object Clicked : UiEvent()