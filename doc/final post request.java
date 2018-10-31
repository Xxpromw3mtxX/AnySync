/*need to create a class for the last auth.
the class will be:*/
private String graphurl;
private URL graphfinal;
private HttpsURLConnection graphpost;
this.graphurl = new String("https://graphql.anilist.co");
this.graphfinal = new URL(graphurl);
private void finalAuth() throws Exception {
  this.graphpost = (HttpsURLConnection) graphfinal.openConnection();
  graphpost.setRequestMethod("POST");
  graphpost.setRequestProperty("Authorization", "Bearer " + access_token);
  graphpost.setRequestProperty("Content-Type", "application/json");
  graphpost.setRequestProperty("Accept", "application/json");
}
