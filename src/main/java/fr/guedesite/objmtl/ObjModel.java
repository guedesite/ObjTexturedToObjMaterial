package fr.guedesite.objmtl;

import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class ObjModel {
	private final List<Vector3f> vertices;
    private final List<Vector2f> textureCoords;
    private final List<Vector3f> normals;
    private final List<Face> faces;

    public ObjModel(List<Vector3f> vertices, List<Vector2f> textureCoords, List<Vector3f> normals, List<Face> faces) {
        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.normals = normals;
        this.faces = faces;
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public List<Vector2f> getTextureCoords() {
        return textureCoords;
    }

    public List<Vector3f> getNormals() {
        return normals;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public static class Face {
        public final int[] vertexIndices;
        public final int[] textureIndices;
        public final int[] normalIndices;

        public Face(int numVertices) {
            this.vertexIndices = new int[numVertices];
            this.textureIndices = new int[numVertices];
            this.normalIndices = new int[numVertices];
        }

        public int[] getVertexIndices() {
            return vertexIndices;
        }

        public int[] getTextureIndices() {
            return textureIndices;
        }

        public int[] getNormalIndices() {
            return normalIndices;
        }
    }
}
