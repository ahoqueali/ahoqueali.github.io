# Install Java 14 using Homebrew

Execute the following commands in terminal:
* `brew update`
* `brew tap adoptopenjdk/openjdk`
* `brew search jdk`
* `brew cask install adoptopenjdk14`

Add the following files to `~/.bash_profile`
* `export JAVA_HOME=$(/usr/libexec/java_home -v14)`
* `export PATH=$PATH:JAVA_HOME/bin`

Finally, update the changes in the current terminal by executing:
* `source ~/.bash_profile`
* `java -version`

# Utils
* List installed JDKs `/usr/libexec/java_home -V`

# Configure MicroSoft Visual Studio
Edit Settings.json file with the following line:

```JSON
"java.configuration.runtimes": [
        {"name":"JavaSE-14","path":"/Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk/Contents/Home", "default": true}]
```

So the complete file will look something like this..

```JSON
{
    "editor.suggestSelection": "first",
    "vsintellicode.modify.editor.suggestSelection": "automaticallyOverrodeDefaultValue",
    "java.configuration.checkProjectSettingsExclusions": false,
    "workbench.startupEditor": "newUntitledFile",
    "java.semanticHighlighting.enabled": true,
    "java.configuration.runtimes": [
        {"name":"JavaSE-14","path":"/Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk/Contents/Home", "default": true}]
}

```

## Reference
* [How to install Java on Mac OS](https://mkyong.com/java/how-to-install-java-on-mac-osx/)
* [Red Hat Setting the JDK](https://marketplace.visualstudio.com/items?itemName=redhat.java)
