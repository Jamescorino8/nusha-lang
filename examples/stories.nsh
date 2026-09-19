Author = {Alice, Bob, Carol, David}
Pet = {Dog, Cat, Bird, Fish}
House = {Red, Blue, Green, Yellow}
Story = [unique Author a, unique Pet p, unique House h]
var Stories : Story[4]
Stories[0].a = Alice
Stories[1].a = Bob
Stories[2].a = Carol
Stories[3].a = David
Stories.a = Alice =>
    Stories.h != Red
Stories.h = Blue =>
    Stories.p = Cat
Stories.a = David =>
    Stories.p = Dog
Stories.a = Carol =>
    Stories.h = Green
Stories.a = Bob =>
    Stories.p != Fish
    Stories.h = Red
