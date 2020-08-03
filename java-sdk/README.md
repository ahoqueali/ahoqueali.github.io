# Install Java 14 using Homebrew

Execute the following commands in terminal:
* `brew update`
* `brew tap adoptopenjdk/openjdk`
* `brew search jdk`
* `brew cask install adoptopenjdk14`

Add the following files to `~/.bash_profile`
* `export JAVA_HOME=$(/usr/libexec/java_home -v14)`
* `PATH=$PATH:JAVA_HOME/bin`


## Reference
* [How to install Java on Mac OS](https://mkyong.com/java/how-to-install-java-on-mac-osx/)
