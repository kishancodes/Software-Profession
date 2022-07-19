#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <iostream>
#include <cstdlib>

using namespace std;


// function declaration 
int explain_Project();          // explains the project to the user.
int uI();                       // displays the user menu and continues the project
int getSingleUserInput();       // continuation of uI. gets the user input X for calculation.
int driverCalc(int X);          // main calculation processs.
int getRangeUserInput();        // caculate from givem range.

// main body of the function
int main ()
{
    // invoke the start of the project
    explain_Project(); 
    // exits the project
    return 0;
}

int getSingleUserInput()
{
    //the user input for X
    int X;
    sleep(2);
    // display the user interface
    printf("\nEnter a positive number for X. Please no letters, no decimals and no number less than 1.");
    printf("\nYour number input for X: ");
    cin >>  X;
    //While the input entered is not an integer, prompt the user to enter an integer.
    while(!cin || X<1)
    {
        printf("\nA positive number is a number greater than 0. Please no decimals.\n");
        printf("A valid input would be '1' or '90' or '2000000'.\n");
        printf("Please enter a valid positive number for X: ");
        cin.clear();
        cin.ignore();
        cin >> X;
    }
    int steps = driverCalc(X);
    sleep(2);
    printf("\n\nSteps taken(see above) to calculate: %d", steps);
    printf("\n\n");
    sleep(4);
    uI();
    
}

int driverCalc(int X)
{  
    int steps=0;
    if(X==1)
    {
        steps++;
        X = ((X*3)+1);
        printf("\n%d",X);
        for(;X>1;)
        {
            if(X%2 == 0){
                steps++;
                X/=2;
                printf("\n%d",X);
            }
            else
            {
                steps++;
                printf("\n%d",X);
                X = ((X*3)+1);
            }
        sleep(1);
        }
    }
    else
    {
        for(;X>1;)
        {
            if(X%2 == 0){
                steps++;
                printf("\n%d",X);
                X/=2;
                if(X==1){
                    printf("\n%d",X);
                }
            }
            else
            {
                steps++;
                printf("\n%d",X);
                X = ((X*3)+1);
            }
        sleep(1);
        }
    }
    return steps;
}

int uI()
{
    
    // declare ui variable for the ui menu user input
    int menuInp;
    // display the user interface
    printf("\nPlease select from the following menu: \n");
    printf("1. Designate a number to the start process.\n");
    printf("2. Designate a range of numbers to use with the process.\n");
    printf("3. Exit the program.\n\n");
    printf("Your selection: ");
    cin >> menuInp;
    //While the input entered is not an integer, prompt the user to enter an integer.
    while(!cin)
    {
        printf("\nPlease insert a valid input.\n");
        printf("\nDo not insert a decimal value or letters.\n");
        printf("\nA valid input would be '1' or '2' or '3'.\n");
        sleep(2);
        printf("\nPlease select from the following menu: \n");
        printf("1. Designate a number to the start process.\n");
        printf("2. Designate a range of numbers to use with the process.\n");
        printf("3. Exit the program.\n\n");
        printf("Your selection: ");
        cin.clear();
        cin.ignore();
        cin >> menuInp;
    }
    switch (menuInp)
    {
        case 1: getSingleUserInput(); // invoke the X calculation process
        break;
        case 2: getRangeUserInput(); // invoke the range calculation process
        break;
        case 3: 
            {
                sleep(2);
                printf("\n\nBye bye.");
                sleep(10);
            }
        break;
        default:
        break;
    }
}

int explain_Project()
{
    //display the description at the start. 
    printf("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    printf("* This is homework 1 of CS 4500 FA 2021 developed by Kishan Bhakta.     *\n");
    printf("* It calculates a mathematical phenomenon: the 3X + 1 problem. Here X   *\n");
    printf("* can be any positive user input as total of 3X + 1. If the your        *\n");
    printf("* number X is even then it gets devided by 2, or, gets multiplied by    *\n");
    printf("* 3 and added with 1 if it is an odd number. The same goes on with      *\n");
    printf("* the results. The cycle will go on until it reaches 1. This program    *\n");
    printf("* will demonstrate how many steps the user input X takes to reach 1.    *\n");
    printf("* It will also calculate steps for a range of numbers and display the   *\n");
    printf("* smallest step and the largest step taken by the numbers within the    *\n");
    printf("* range and total average steps taken by the total range of numbers.    *\n");
    printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    sleep(1);
    uI(); // invoke the user interface menu.
}

int getRangeUserInput()
{
    int s, l; // s for smallest value, l for largest value
    // start with small
    sleep(2);
    printf("\nYour number input for smallest value of the range: ");
    cin >>  s;
    while(!cin || s<1)
    {
        printf("\nPlease enter a valid positive number for the starting point of the range: ");
        cin.clear();
        cin.ignore();
        cin >> s;
    }
    //end with large
    sleep(2);
    printf("\nYour number input for largest value of the range: ");
    cin >>  l;
    while(!cin || l<=s)
    {
        printf("\nPlease enter a valid larger positive number for the ending point of the range: ");
        cin.clear();
        cin.ignore();
        cin >> l;
    }
    
    int stepsLength = ( l - s ) + 1;
    int steps[stepsLength]; // create array for storing steps taken to calculate
    int stp = 0; // to start the array from the first point
    sleep(2);
    printf("\n\nCalculating steps starting from: %d\n",s);
    sleep(2);
    for(int i=s;i<=l;i++)
    {
        steps[stp] = driverCalc(i);
        printf("\n");
        sleep(1);
        printf("\nSteps taken(see above) to calculate for %d is: %d\n", i, steps[stp]);
        stp++;
        sleep(2);
    }
    sleep(3);
    int temps = 100000000; //store resulted compared values in temps
    //find the smallest
    stp = 0;
    int temp2 = 0 ;
    for(int i=s;i<=l;i++)
    {
        if(stp == 0){
            temps = steps[stp];
            temp2 = i;
        }
        else
        {
            if(temps > steps[stp])
            {
                temps = steps[stp];
                temp2 = i;
            }
        }
        stp++;
    }
    printf("\n\nThe smallest steps taken was for %d, which is: %d\n", temp2, temps);
    sleep(3);
    //find the largest
    temps = 100000000;
    stp = 0;
    temp2 = 0;
    for(int i=s;i<=l;i++)
    {
        if(stp == 0){
            temps = steps[stp];
            temp2 = i;
        }
        else
        {
            if(temps < steps[stp])
            {
                temps = steps[stp];
                temp2 = i;
            }
        }
        stp++;
    }
    printf("\nThe largest steps taken was for %d, which is: %d\n", temp2, temps);
        sleep(3);
    //find the average
    stp = 0;
    temp2 = 0;
    for(int i =s;i<=l;i++)
    {
        temp2 += steps[stp];
        stp++;
    }
    double temps3 = (float)temp2 / stepsLength;
    printf("\nThe total average steps taken for all numbers was: %f steps per number in the range.\n", temps3);
    printf("\n");
    sleep(5);
    uI();
}

