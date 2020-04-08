package utils

import api.enities.Project
import org.json.simple.JSONObject

class ProjectParser {

//    Project fromJson(JSONObject json) throws IllegalComponentException {
//
//
//        JSONObject jProject = json.get("project") as JSONObject
//
//        STBScheme scheme = new GraphMultiPageScheme()
//        STBPage currentPage
//
//        String projectName = (String) jProject.get("name")
//        JSONArray jPages = (JSONArray) jProject.get("pages")
//        Iterator<JSONObject> iterator = jPages.iterator()
//        while (iterator.hasNext()) {
//            JSONObject jPageObject = iterator.next()
//            JSONObject jPage = (JSONObject) jPageObject.get("page")
//            if (null == jPage) {
//                jPage = (JSONObject) jPageObject.get("resultpage")
//                // TODO: check type
//                STBGraph pageGraph = GraphDataAccess.EUCLIDEAN.fromJson(jPage)
//                currentPage = new ResultGraphPage(pageGraph)
//                Map<String, String> properties = new HashMap<>()
//                jPage.forEach((name, property) -> {
//                    if (name instanceof String && property instanceof String)
//                        properties.put((String) name, (String) property)
//                })
//                ((ResultGraphPage) currentPage).putProperties(properties)
//            } else {
//                // TODO: check type
//                STBGraph pageGraph = GraphDataAccess.EUCLIDEAN.fromJson(jPage)
//                currentPage = new GraphPage(pageGraph)
//            }
//            String pageName = (String) jPage.get("name")
//            currentPage.nameProperty().set(pageName)
//            scheme.addPage(currentPage)
//        }
//        return new SimpleProject((GraphMultiPageScheme) scheme, projectName)
//    }
}
