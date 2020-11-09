package io.github.joxit.osm.service;

import io.github.joxit.osm.model.Tile;
import io.github.joxit.osm.utils.Svg;
import mil.nga.sf.geojson.GeoJsonObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Service pour retourner les tuiles.
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
@Service
public class TileService {


  /**
   * Ici il faut prendre les coordonnées de la tuile et renvoyer la donnée PNG associée.
   * Vous pouvez y ajouter des fonctionnalités en plus pour améliorer les perfs.
   *
   * @param tile qu'il faut renvoyer
   * @return le byte array au format png
   */
  public byte[] getTile(Tile tile) {

    if (tile.getZ()<0 || tile.getX()<0 || tile.getY()<0){
      throw new IllegalArgumentException("un ou plusieurs arguments sont negatifs");
    }
    if(tile.getZ()>24){
      throw new IllegalArgumentException("l'argument z(zoom) ne doit pas depasser 24");
    }
    return Svg.getTile(tile);
  }

  /**
   * @return le contenu du fichier prefectures.geojson
   */

  public String getPrefectures() {
    try (FileReader fileReader= new FileReader("C:\\Users\\Toshiba 5eme\\Desktop\\USPN\\LEE3\\IG-Master2-master\\osm\\osm-core\\src\\main\\resources\\prefectures.geojson")){
      JSONParser jsonParser=new JSONParser();
      String prefectures = jsonParser.parse(fileReader).toString();
      return  prefectures;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Il faudra créer votre DAO pour récuperer les données.
   * Utilisez ce que vous voulez pour faire le DAO.
   *
   * @return les éléments contenus dans la base de données
   */
  public GeoJsonObject getPOIs() {
    return null;
  }
}
