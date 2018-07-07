package com.gaurav.ubertest.loading.model;

/**
 * Created by gauravn on 05/07/18.
 */

public class PhotoModel {
  public static String BASEURL = "http://farm%s.static.flickr.com/%s/%s_%s.jpg";
  private String id;
  private String owner;
  private String secret;
  private String server;
  private Integer farm;
  private String title;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getServer() {
    return server;
  }

  public void setServer(String server) {
    this.server = server;
  }

  public Integer getFarm() {
    return farm;
  }

  public void setFarm(Integer farm) {
    this.farm = farm;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String buildUrl(){
    return String.format(BASEURL, farm, server,
            id, secret);
  }
}