# Platform-Science-Code-Exercise
***
## Description:

Coding exercise for a position with Platform Science. 
The application consists of 2 fragments. One which displays a list of drivers extracted from a provided Json file which includes also includes a list of shipments addresses. The other displays the selected driver and the shipment address from the same Json with the best Sustainability Score. The  Sustainability Score is determined by a algorithm with these set of rules:
 
* If the length of the shipment's destination street name is even, the base suitability score
(SS) is the number of vowels in the driver’s name multiplied by 1.5.
* If the length of the shipment's destination street name is odd, the base SS is the number
of consonants in the driver’s name multiplied by 1.
* If the length of the shipment's destination street name shares any common factors
(besides 1) with the length of the driver’s name, the SS is increased by 50% above the
base SS.

## Technologies used:

* MVVM
* Kotlin Serialization
* ViewModels
* Fragments
* Databinding
* RecyclerView
* BindingAdapters
* Coroutines
* Navigation Component

## How to run the code:

* Clone the code from the repository into IDE 
* Run build and run the code on either an emulator or physical device 


