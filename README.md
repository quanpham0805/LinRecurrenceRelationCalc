## Linear Recurrence Relation Calculator

I decided to create this project to test RecyclerView, View Model and Navigation component, as well as practicing Competitive Programming in Java.

#### Descriptions:
- User define a recurrence relation of their own, follow the definition in the app.
- User can define a recurrence relation with up to 100 "known" terms and coefficients with limit up to 10^9 - 1.
- Input the modulo to avoid integer overflow (1 <= m <= 10^18 - 1). The reason is mobile device has limited storage, and thus we cannot afford to show every digits on the screen.
- Input the desired term you want to calculate. This can be any integer value less than 10^18 - 1.
- Time complexity is O(m^3 log2(n)), where m is the number of terms/coefficients, while n is the n-th term.


#### Some images:

Fibonacci sequence with first and second term = 1, we have the 30th term is 832040.

<img src="https://github.com/quanpham0805/LinRecurrenceRelationCalc/blob/master/img/FiboSmall.png" width="30%">

Fibonacci sequence but calculating the 100000000000000000-th (1e17-th) term.

<img src="https://github.com/quanpham0805/LinRecurrenceRelationCalc/blob/master/img/FiboHuge.png" width="30%">

User can easily modify the terms and coefficients.

<img src="https://github.com/quanpham0805/LinRecurrenceRelationCalc/blob/master/img/Mod.png" width="30%">
