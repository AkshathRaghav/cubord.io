

<p align="center">
  <img width="1100" src="https://cdn.discordapp.com/attachments/812010489248088088/866530400582762506/finalcover.png" alt="cubot">
</p>

<br> 

<p align="center">
  <a href="https://github.com/Rapptz/discord.py/">
     <img src="https://img.shields.io/badge/discord-java-blue.svg" alt="discord.java">
  </a>
  <a href="https://github.com/Cog-Creators/Red-DiscordBot/actions">
    <img src="https://img.shields.io/github/workflow/status/Cog-Creators/Red-Discordbot/Tests?label=tests" alt="GitHub Actions">
  </a>
    <a href="http://red-discordbot.readthedocs.io/en/stable/?badge=stable">
    <img src="https://readthedocs.org/projects/red-discordbot/badge/?version=stable" alt="Red on readthedocs.org">
  </a>
</p>
<p align="center">

  <a href="https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity">
    <img src="https://img.shields.io/badge/Maintained%3F-yes-green.svg" alt="Maintainace">


  <a href="http://makeapullrequest.com">
    <img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg">
  </a>

  <a href="https://discord.gg/HjJCwm5">
        <img src="https://img.shields.io/discord/308323056592486420?logo=discord"
            alt="Discord"></a>

</p>

<p align="center">
  <a> <img src = "https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" > </a> 
  
</p>


<p align="center">
  <a href="#overview">Overview</a>
  •
  <a href="#setup">Setup</a>
  •
  <a href="#documentation">Documentation</a>
  

</p>

<br> 


# 📔 Overview

<img align="right" src="https://cdn.discordapp.com/attachments/812010489248088088/875648580252233738/Comp_Cube.png" height="280" width="280">

<br> 

*Ever wanted to cube, but didnt have a cube at hand? <br> Or perhaps you wanted to learn, or just wanted a simple solution to your scrambled cube? <br> Maybe you want to visualize how it would look?*
<br> *or maybe you are a 'Fewest Moves' enthusiast and want to check your solution!* <br>

Fret no more, for 
<br> --> Cubord just landed. 

> *Cub-ord, Disc-ord -> get it?* 
 <br> 
 
*Cubord* is the implementation of the [cubot.io](https://github.com/AkshathRaghav/cubot.io) library for discord. In other words, cubord is a cubing discord bot. <br>  
<br> 
Basically, all of the methods in cubot.io have been used in Cubord's code. But Cubord offers more than just some library outputs. It stores your cube, lets you time yourself, averages your times, shows you your cube and more.
<br> <br> 

<p align="center">
  <img width="1000" src="https://cdn.discordapp.com/attachments/812010489248088088/878341674269548554/cfop.png" alt="cubot">
</p>
<br> 


# ℹ️ Usage  

#### **Cubord** is continously developing and new features are constantly added. Read the guide [here](https://discord.gg/red)! <br> 

## Making a Cube 


###  /make 


* Add a String element to the {temp String[]} with the first letters of each color you see on a face, in row-major order.
* While entering the White and Yellow faces, remember to keep the Green face facing you ( as shown in the pictures below )
* The picture below is for 3x3, but do the same for 2x2

<div align = "center"> 
  <img src="https://camo.githubusercontent.com/f654b8ffdf1335fc93aba3cf72edb86cb5d808377a88fca979c6a04b29ab5292/68747470733a2f2f63646e2e646973636f72646170702e636f6d2f6174746163686d656e74732f3831323031303632343330313236393031322f3834363031383737313930373131373038362f556e7469746c65645f64657369676e2e706e67" height="245" width="190">
<img width="710" src= "https://cdn.discordapp.com/attachments/812010489248088088/876104870900428890/nKi1CdYAEZ.gif"> 
</div> 


### /makeSolved 
 
__Option__ : *3 for 3x3, 2 for 2x2, empty for solving held cube*

<div align = "center"> 
<img width="430" src= "https://cdn.discordapp.com/attachments/812010489248088088/876104872712372244/OBntxp0L6C.gif">  
<img width="480" height="312" src= "https://cdn.discordapp.com/attachments/812010489248088088/876104868094423150/rkc7giaKn6.gif">  
</div> 

## Executing Moves

### /do 
__Options__ : 
* *moves - Moves to be executed* 
* *show_type - s -> shows your changed cube, empty -> doesnt show your changed cube on chat*

<p align="center">
  <img width="1000" src="https://cdn.discordapp.com/attachments/876060092632530975/878888125265494036/vvUpdrEuE3.gif" alt="cubot">
</p>

### /rev
__Options__ : 
* *moves - Moves to be reverse executed* 
* *mode - s -> returns only the reversed algorithm, empty -> executes your moves on the cube*


<p align="center">
  <img width="1000" src="https://cdn.discordapp.com/attachments/812010489248088088/878889682803515402/brckcbclQs.gif" alt="cubot">
</p>

## Solving the Cube 

### /solve
Solves your cube and returns the solution. 
Lets you know if you have entered an impossible cube or something is wrong in your input.

<div align = "center"> 
<img width="470" height = "280" src= "https://cdn.discordapp.com/attachments/812010489248088088/878893412680626196/AJIDNCP0zq_-_Copy.gif">  
<img width="460" height="280" src= "https://cdn.discordapp.com/attachments/812010489248088088/878894016480034826/I0VtiXR0WV.gif">  
</div> 
 
## Timing 

### /addTime
__Options__ : 
* *mins - Number of minutes; 0 if nothing* 
* *secs - Number of seconds*
* *ms - Number of milliseconds*

<img src= "https://cdn.discordapp.com/attachments/812010489248088088/878897869078294589/Discord_i1qmzgIgvy.png">  

### /get_best
Tells you your best time!

### /get_avg
Tells you your average time!

## For the entire command list and an explanation for each of them, read the [Docs]()

# ⚙️ Setup 

* Click [here]() to authorize the bot for your server 
* Choose the server you want to add Cubord to
* Authorize the bot 

<p align="center">
  <img width="900" src="https://cdn.discordapp.com/attachments/812010489248088088/878344774661328996/cubot.io_12.png" alt="cubot">
</p>


# 💁 Support 
#### If you have any suggestions for the bot, or any new commands to be added, use " /suggest " on discord and I'll look into it!  <br> 
#### Alternatively, join the [Discord Support Server for Cubord]() 

