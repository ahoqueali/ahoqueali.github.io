# Install Java 14 using Homebrew

Execute the following commands in a terminal:
* `brew update`
* `brew tap adoptopenjdk/openjdk`
* `brew search jdk`
* `brew cask install adoptopenjdk14`

Add the the following files in `~/.bash_profile`
* Add `export JAVA_HOME=$(/usr/libexec/java_home -v14)`
* Add `PATH=$PATH:JAVA_HOME/bin`


## Reference
* [How to install Java on Mac OS](https://mkyong.com/java/how-to-install-java-on-mac-osx/)
