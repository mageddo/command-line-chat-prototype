# procurar os arquivos fonte
find -name "*.java" > sources.txt

# compilando o código
javac @options.txt @sources.txt
