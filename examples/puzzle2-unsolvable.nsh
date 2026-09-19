Student = {Emily, Frank, Grace, Hugo}
Project = {Volcano, Robot, SolarSystem, Bridge}
Material = {Clay, Metal, Paper, Wood}
Booth = {A, B, C, D}
Presentation = [unique Student s, unique Project p, unique Material m, unique Booth b]
var Presentations : Presentation[4]
Presentations[0].s = Emily
Presentations[1].s = Frank
Presentations[2].s = Grace
Presentations[3].s = Hugo
Presentations.p = Volcano =>
    Presentations.m = Clay
    Presentations.b = A
Presentations.s = Grace =>
    Presentations.p != Robot
    Presentations.p != SolarSystem
Presentations.s = Hugo =>
    Presentations.b = D
Presentations.p = Bridge =>
    Presentations.m = Wood
Presentations.s = Emily =>
    Presentations.b != B
Presentations.p = Robot =>
    Presentations.m = Metal
Presentations.p = SolarSystem =>
    Presentations.m != Paper
