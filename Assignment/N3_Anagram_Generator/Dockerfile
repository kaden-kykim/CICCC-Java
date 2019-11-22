FROM openjdk:8-alpine
COPY . /usr/src/anagram
WORKDIR /usr/src/anagram
RUN javac -Xlint -d bin -sourcepath src src/ca/ciccc/wmad/kaden/assignment/n3/AnagramGenerator.java
CMD ["java", "-cp", "bin", "ca.ciccc.wmad.kaden.assignment.n3.AnagramGenerator"]
