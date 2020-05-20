package test

import api.ProjectFiles

println "RandomGraphTest started"

int count = Math.random() * 97 + 3 as int
println "Points count is: ${count}"

File graphFile = new File(ProjectFiles.GRAPHS_DIRECTORY, "${(System.currentTimeMillis() / 1000 as long) % 10_000_000}.${count}")
println "Name is: ${graphFile.name}"

graphFile.withPrintWriter {
    it << "${count}\n"
    for (int i = 0; i < count; i++) {
        it << "${Math.random()} ${Math.random()}\n"
    }
}

println "RandomGraphTest finished"
