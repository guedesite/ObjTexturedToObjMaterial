package fr.guedesite.objmtl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class converter {
	
	public static void main(String[] args) throws IOException {
        String inputObjPath = "input.obj";
        String inputTexturePath = "texture.png";
        String outputObjPath = "promo.obj";
        String outputMtlPath = "promo.obj.mtl";

        
        ObjModel objModel = ObjReader.readObj(inputObjPath);
        BufferedImage textureImage = ImageIO.read(new File(inputTexturePath));
        

        Map<Color, List<ObjModel.Face>> colorToFaceMap = new HashMap<>();
        for (ObjModel.Face face : objModel.getFaces()) {
            Color faceColor = getFaceColor(face, objModel, textureImage);
            colorToFaceMap.computeIfAbsent(faceColor, k -> new ArrayList<>()).add(face);
        }
        String mtlLibName = Paths.get(outputMtlPath).getFileName().toString();
        try (PrintWriter mtlWriter = new PrintWriter(outputMtlPath)) {
            try (PrintWriter objWriter = new PrintWriter(outputObjPath)) {
                objWriter.println("mtllib " + mtlLibName);
                for (Vector3f v : objModel.getVertices()) {
                    objWriter.println("v " + v.x + " " + v.y + " " + v.z);
                }
                for (Vector3f vn : objModel.getNormals()) {
                    objWriter.println("vn " + vn.x + " " + vn.y + " " + vn.z);
                }

                int mtlCounter = 0;
                for (Map.Entry<Color, List<ObjModel.Face>> entry : colorToFaceMap.entrySet()) {
                    Color color = entry.getKey();
                    List<ObjModel.Face> faces = entry.getValue();

                    String mtlName = "material_" + mtlCounter++;
                    objWriter.println("usemtl " + mtlName);

                    for (ObjModel.Face face : faces) {
                        objWriter.println("f " + faceToString(face));
                    }

                    mtlWriter.println("newmtl " + mtlName);
                    mtlWriter.println("Kd " + color.getRed() / 255.0 + " " + color.getGreen() / 255.0 + " " + color.getBlue() / 255.0);
                    mtlWriter.println("Ns 10");
                    mtlWriter.println("Ni 1.5");
                }
            }
        }
    }
	
	private static Color getFaceColor(ObjModel.Face face, ObjModel objModel, BufferedImage textureImage) {
        Vector2f textureCoord = objModel.getTextureCoords().get(face.textureIndices[0]);
        int pixelX = (int) (textureCoord.x * (textureImage.getWidth() - 1));
        int pixelY = (int) ((1 - textureCoord.y) * (textureImage.getHeight() - 1));
        return new Color(textureImage.getRGB(pixelX, pixelY));
    }
	
	private static String faceToString(ObjModel.Face face) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < face.vertexIndices.length; i++) {
		sb.append(face.vertexIndices[i] + 1);
		sb.append('/');
		sb.append(face.textureIndices[i] + 1);
		sb.append('/');
		sb.append(face.normalIndices[i] + 1);
		 if (i < face.vertexIndices.length - 1) {
	            sb.append(' ');
	        }
	    }
	    return sb.toString();
	}

	
}
