

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

  
</p>

<p align="center">
  <a> <img src = "https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" > </a> 
  
</p>


<p align="center">
  <a href="#overview">Overview</a>
  •
  <a href="#setup">Setup</a>
  •
  <a href="#usage">Usage</a>
  •
  <a href="#support">Support</a>
  
  

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


# ⚙️ Setup 

* Click [here](https://discord.com/api/oauth2/authorize?client_id=847111410617679894&permissions=517543946240&scope=applications.commands%20bot) to authorize the bot for your server 
* Choose the server you want to add Cubord to
* Authorize the bot 

<p align="center">
  <img width="950" src="https://cdn.discordapp.com/attachments/812010489248088088/878344774661328996/cubot.io_12.png" alt="cubot">
</p>


# ℹ️ Usage  

#### **Cubord** is continously developing and new features are constantly added. Read the guide [here](https://discord.gg/red)! <br> Use "/speak" for quick tips! <br> 


<p align="center">
  <img width="950" src="https://cdn.discordapp.com/attachments/709067090769870942/874209373063372800/ezgif.com-gif-maker.gif" alt="cubot">
</p>

## Making a Cube 


###  /make 

 <img src="https://camo.githubusercontent.com/f654b8ffdf1335fc93aba3cf72edb86cb5d808377a88fca979c6a04b29ab5292/68747470733a2f2f63646e2e646973636f72646170702e636f6d2f6174746163686d656e74732f3831323031303632343330313236393031322f3834363031383737313930373131373038362f556e7469746c65645f64657369676e2e706e67" align = "left" height = "150" > 

* Add the colors of each piece in a face, in the order shown alongside, in captial letters 

<br> 

* While entering the White and Yellow faces, remember to keep the Green face facing you 

<br> 

* The picture on the left is for 3x3, but follow this order for 2x2 as well 

<div align = "center" style = "inline" > 
<img src= "https://cdn.discordapp.com/attachments/876060092632530975/901076726447243294/pVUTATOJ39.gif"> 
</div> 


### /makeSolved 
 
__Option__ : *3 for 3x3, 2 for 2x2, empty for solving held cube*

<div align = "center" style = "inline"> 
<img  src= "https://cdn.discordapp.com/attachments/709067090769870942/901081091023175710/jkkYrUrhBx.gif">

</div> 

## Executing Moves

### /do 
__Options__ : 
* *moves - Moves to be executed* 
* *show_type - s -> shows your changed cube, empty -> doesnt show your changed cube on chat*

<p align="center">
  <img width="1000" src="https://cdn.discordapp.com/attachments/876104213464219658/901078166704111656/JGeaH8ppZ5.gif" alt="cubot">
</p>


### /rev
__Options__ : 
* *moves - Moves to be reverse executed* 
* *mode - s -> returns only the reversed algorithm, empty -> executes your moves on the cube*


<p align="center">
  <img width="1000" src="https://cdn.discordapp.com/attachments/876104213464219658/901078789554065418/neFgcdp1w4.gif" alt="cubot">
</p>

## Solving the Cube 

### /solve
Solves your cube and returns the solution. 
Lets you know if you have entered an impossible cube or something is wrong in your input.

<p align="center">
  <img width="1000" src="https://cdn.discordapp.com/attachments/876104213464219658/901079623343956008/cyFx6o1T5y.gif" alt="cubot">
</p>

## Timing 

### /addTime
__Options__ : 
* *mins - Number of minutes; 0 if nothing* 
* *secs - Number of seconds*
* *ms - Number of milliseconds*


### /get_best
Tells you your best time!

### /get_avg
Tells you your average time!

### For the entire command list and an explanation for each of them, read the [Docs](https://github.com/AkshathRaghav/cubord.io/tree/master/Docs)

<br> 


# 💁 Support 
#### If you have any suggestions for the bot, or any new commands to be added, use " /suggest " on discord and I'll look into it!  <br> 
#### If you are facing any issue in the /solve command, send me your scramble through the same method

