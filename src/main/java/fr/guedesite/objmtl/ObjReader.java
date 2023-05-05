package fr.guedesite.objmtl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class ObjReader {
	  public static ObjModel readObj(String filePath) throws IOException {
	        List<Vector3f> vertices = new ArrayList<>();
	        List<Vector2f> textureCoords = new ArrayList<>();
	        List<Vector3f> normals = new ArrayList<>();
	        List<ObjModel.Face> faces = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split("\\s+");
	                if (parts.length == 0) continue;

	                switch (parts[0]) {
	                    case "v" -> vertices.add(new Vector3f(
	                            Float.parseFloat(parts[1]),
	                            Float.parseFloat(parts[2]),
	                            Float.parseFloat(parts[3])));
	                    case "vt" -> textureCoords.add(new Vector2f(
	                            Float.parseFloat(parts[1]),
	                            Float.parseFloat(parts[2])));
	                    case "vn" -> normals.add(new Vector3f(
	                            Float.parseFloat(parts[1]),
	                            Float.parseFloat(parts[2]),
	                            Float.parseFloat(parts[3])));
	                    case "f" -> {
	                        ObjModel.Face face = new ObjModel.Face(parts.length - 1);
	                        for (int i = 1; i < parts.length; i++) {
	                            String[] indices = parts[i].split("/");
	                            face.vertexIndices[i - 1] = Integer.parseInt(indices[0]) - 1;
	                            face.textureIndices[i - 1] = Integer.parseInt(indices[1]) - 1;
	                            face.normalIndices[i - 1] = Integer.parseInt(indices[2]) - 1;
	                        }
	                        faces.add(face);
	                    }
	                }
	            }
	        }

	        return new ObjModel(vertices, textureCoords, normals, faces);
	    }
}
