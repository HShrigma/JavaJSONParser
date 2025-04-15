#!/bin/bash

echo "🔧 Compiling Java files..."

# Compile all .java files in the current directory
javac *.java

# Check if compilation succeeded
if [ $? -eq 0 ]; then
    echo "🚀 Running Main..."
    java Main
else
    echo "❌ Compilation failed."
fi
