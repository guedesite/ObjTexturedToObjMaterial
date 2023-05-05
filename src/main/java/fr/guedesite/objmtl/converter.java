package fr.guedesite.objmtl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class converter {
	
	private static List<Vertex4d> vertices;
	private static List<Vertex4d> normals;
	private static List<Vertex3d> textures;
	private static List<Face> faces;
	
	

	public static void main(String[] args) {
		args = new String[] {"C:\\Users\\guedesite\\AppData\\Roaming\\.Estrilion\\assets\\launcher\\render\\mage_feu.obj" };

		if(args.length == 0) {
			System.out.println("Missing files");
			return;
		} else {
			vertices = new ArrayList<>();
			normals = new ArrayList<>();
			textures = new ArrayList<>();
			faces = new ArrayList<>();
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(new File(args[0])));
				String line = reader.readLine();
				int iv = 1, ivt = 1, ivn = 1;
				while (line != null) {
					//System.out.println(line);
					line = reader.readLine();
					if(line == null) {
						continue;
					}
					String[] split = line.split(" ");
					if(split.length > 0) {
						
						
						if(split[0].equals("v")) {
							Vertex4d test = new Vertex4d(Double.parseDouble(split[1]), Double.parseDouble(split[2]),Double.parseDouble(split[3]), iv);
							//if(!vertices.stream().anyMatch(x -> x.equals(test))) {
								vertices.add(test);
							//}
								System.out.println(split[0] + " "+test+" "+iv);
								iv++;
						} else if(split[0].equals("vt")) {
							Vertex3d test = new Vertex3d(Double.parseDouble(split[1]), Double.parseDouble(split[2]), ivt);
							//if(!textures.stream().anyMatch(x-> x.equals(test))) {
								textures.add(test);
							//}
								System.out.println(split[0] + " "+test+" "+ivt);
								ivt++;
						} else if(split[0].equals("vn")) {
							Vertex4d test = new Vertex4d(Double.parseDouble(split[1]), Double.parseDouble(split[2]),Double.parseDouble(split[3]), ivn);
							//if(!normals.stream().anyMatch(x -> x.equals(test))) {
								normals.add(test);
							//}
								System.out.println(split[0] + " "+test+" "+ivn);
								ivn++;
						} else if(split[0].equals("f")) {
							int nb = 4;
							 Vertex4d[] v = new Vertex4d[nb];
							 Vertex3d[] vt = new Vertex3d[nb];
							 Vertex4d[] vn = new Vertex4d[nb];
							 for(int i2 = 0; i2 < nb; i2++) {
								String[] t = split[1+i2].split("/");
								//System.out.println(Integer.parseInt(t[0]) + " "+Integer.parseInt(t[1]) + " "+Integer.parseInt(t[2]));
								int tmpv2 = Integer.parseInt(t[0]);
								for( Vertex4d tmpv:vertices) {
									if(tmpv.getW() == tmpv2) {
										boolean flag = false;
										for(Face f:faces) {
											for(int i = 0; i < nb; i++) {
												if(f.vertices[i].getX() == tmpv.getX() && f.vertices[i].getY() == tmpv.getY() && f.vertices[i].getZ() == tmpv.getZ()) {
													v[i2] = f.vertices[i];
													flag = true;
												}
											}
											if(flag) {
												break;
											}
										}
										if(!flag) {
											v[i2] = tmpv;
										}
									}
								}
								
								int tmpvt2 = Integer.parseInt(t[0]);
								for( Vertex3d tmpvt:textures) {
									if(tmpvt.getZ() == tmpvt2) {
										vt[i2] = tmpvt;
									}
								}
								
								int tmpvn2 = Integer.parseInt(t[0]);
								for( Vertex4d tmpvn:normals) {
									if(tmpvn.getW() == tmpvn2) {
										vn[i2] = tmpvn;
									}
								}
								/*
								v[i2] = vertices.stream().findFirst().filter(x -> x.getW() == Integer.parseInt(t[0])).get();
								vt[i2] = textures.stream().findFirst().filter(x -> x.getZ() == Integer.parseInt(t[1])).get();
								vn[i2] = normals.stream().findFirst().filter(x -> x.getW() == Integer.parseInt(t[2])).get(); */
							 }
							 Face f = new Face(v, vt, vn);
							 System.out.println(f.toString());
							 faces.add(f);
						}
					}
					
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	static class Face {
		
		public Vertex4d[] vertices;
		public Vertex3d[] textures;
		public Vertex4d[] normals;
		
		public Face(Vertex4d[] vertices, Vertex3d[] textures, Vertex4d[] normals) {
			this.vertices = vertices;
			this.textures = textures;
			this.normals = normals;
		}
		
		@Override
		public String toString() {
		    return "Face { " +
		            "vertices = " + Arrays.toString(vertices) +
		            ", textures = " + Arrays.toString(textures) +
		            ", normals = " + Arrays.toString(normals) +
		            " }";
		}
	}
	
}
