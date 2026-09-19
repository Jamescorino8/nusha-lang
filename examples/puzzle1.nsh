Person = {Aria, Blake, Chris, Drew}
Subject = {Math, History, Biology, Art}
Desk = {Red, Blue, Green, Yellow}
Minutes = {30, 45, 60, 90}
Study = [unique Person p, unique Subject s, unique Desk d, unique Minutes m]
var Sessions : Study[4]
Sessions[0].p = Aria
Sessions[1].p = Blake
Sessions[2].p = Chris
Sessions[3].p = Drew
Sessions.s = Math =>
    Sessions.d = Blue
    Sessions.m = 45
Sessions.p = Chris =>
    Sessions.d = Yellow
Sessions.s = Biology =>
    Sessions.d = Green
    Sessions.m = 60
Sessions.p = Aria =>
    Sessions.s != History
    Sessions.d != Red
Sessions.p = Drew =>
    Sessions.s != Art
Sessions.s = History =>
    Sessions.m = 90
Sessions.d = Red =>
    Sessions.m != 30
