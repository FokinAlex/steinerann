package utils.orl

import api.ProjectFiles
import log.LogFacade

class OrlDataConvertTools {

    private static final def NUMBERS = [1, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100]

    static void main(String[] args) {
        Map<File, File> files = new HashMap<>()
        for (number in NUMBERS) {
            File rawCaseFile = new File(ProjectFiles.ORL_RAW_DIRECTORY, "estein${number}.txt")
            File rawResultFile = new File(ProjectFiles.ORL_RAW_DIRECTORY, "estein${number}opt.txt")
            files.put(rawCaseFile, rawResultFile)
        }

        Set<Case> orlCases = new HashSet<>()
        files.each { orlCases.addAll(readOrlCases(it.getKey(), it.getValue()))}

        for (int i = 0; i <= 100; i++) {
            int counter = 1
            for (c in orlCases) {
                if (c.points.size() == i) {
                    File file = new File(ProjectFiles.ORL_CASES_DIRECTORY, "${i}-${counter++}.orl")
                    c.writeToFile(file)
                    LogFacade.INFO "[v] ${file}"
                }
            }
        }
    }

    /**
     * The format of these data files is:
     * number of test problems (m)
     * for each test problem i (i = 1, ... , m) in turn:
     *     number of points (n)
     *     for each point j (j = 1, ... , n): x-coordinate, y-coordinate
     *
     * The format of these files is:
     * number of test problems (m)
     * for each test problem i (i = 1, ... , m) in turn:
     *     optimal steiner solution value
     *     minimal spanning tree solution value
     *     number of Steiner points (S)
     *     for each Steiner point s (s = 1, ... , s): x-coordinate, y-coordinate
     *
     * (c) J. E. Beasley
     *
     * @param casesFile
     * @param resultsFile
     * @return
     */
    private static Set<Case> readOrlCases(File casesFile, File resultsFile) {
        Set<Case> cases = new HashSet<>()
        Case currentCase
        int countOfPoint
        int countOfTasks
        String[] location
        double x
        double y
        casesFile.withReader { casesReader ->
            resultsFile.withReader { resultsReader ->
                countOfTasks = Integer.valueOf(casesReader.readLine().trim())
                resultsReader.readLine()
                for (int i = 0; i < countOfTasks; i++) {
                    cases.add(currentCase = new Case())
                    countOfPoint = Integer.valueOf(casesReader.readLine().trim())
                    for (int j = 0; j < countOfPoint; j++) {
                        location = casesReader.readLine().trim().split(' ')
                        x = Double.valueOf(location[0])
                        y = Double.valueOf(location[1])
                        currentCase.addPoint(x, y)
                    }
                    currentCase.setSmt(Double.valueOf(resultsReader.readLine().trim()))
                    currentCase.setMst(Double.valueOf(resultsReader.readLine().trim()))
                    countOfPoint = Integer.valueOf(resultsReader.readLine().trim())
                    for (int j = 0; j < countOfPoint; j++) {
                        location = resultsReader.readLine().trim().split(' ')
                        x = Double.valueOf(location[0])
                        y = Double.valueOf(location[1])
                        currentCase.addSteinerPoint(x, y)
                    }
                }
            }
        }
        cases
    }
}
