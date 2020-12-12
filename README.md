# Planterria
Since this is an android application in order to run it you must have android studio installed with a Java SDK of 21 or higher.
Also, please ensure that you are running this on a machine that has x86 cpu architecture for the android emulator. This is on most commonly used Intel Cpu's.
To add, to run this application in android studio you need to do make sure you have a suitable android emulator installed in android studio. 
Typically their is an emulator preinstalled. However if you do not have one follow the following steps: 
Go to Tools ->AVD manager-> Create Virtual Device-> Pixel 4->Next->x86 Images->R->Next->Finish 
Next, make sure you have forked the Planterria to a local directory. 
Open that directory in android studio and run the app in the emulator with the play button. Enjoy.

Notes on current functionality. As is their is their is no authentication. Thus, all plants that you store on your device are shared access all instances of the application. The garden is shared in the cloud as a proof of concept that all information does not need to be locally stored on the device. Thus, each garden can be made user specific in the future with the authentication feature built into firebase.



