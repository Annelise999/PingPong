package com.example.pp;

import java.io.Serializable;

public class Match implements Serializable {

    private long id;
    private String joueur1;
    private String joueur2;
    private String gagnant;
    private int joueur_service;
    private int balle;
    private int aces_j1;
    private int aces_j2;
    private int fautes_j1;
    private int fautes_j2;
    private int let_j1;
    private int let_j2;
    private int manches_j1;
    private int manches_j2;

    private int current_manche;

    private int pts_j1;
    private int pts_j2;
    private int pts_manche1_j1;
    private int pts_manche1_j2;
    private int pts_manche2_j1;
    private int pts_manche2_j2;
    private int pts_manche3_j1;
    private int pts_manche3_j2;
    private float lat;
    private float lng;

    public Match(){
        super();
        id = 0;
        current_manche=0;
        joueur1 = "";
        joueur2 = "";
        gagnant= "";
        joueur_service=1;
        balle = 0;
        aces_j1= 0;
        aces_j2= 0;
        fautes_j1=0;
        fautes_j2=0;
        let_j1=0;
        let_j2=0;
        manches_j1=0;
        manches_j2=0;
        pts_manche1_j1=0;
        pts_manche1_j2=0;
        pts_manche2_j1=0;
        pts_manche2_j2=0;
        pts_manche3_j1=0;
        pts_manche3_j2=0;
        pts_j1=0;
        pts_j2=0;
    }



    public Match(String joueur1, String joueur2, int joueur_service){
        super();
        balle= 0;
        aces_j1= 0;
        aces_j2= 0;
        fautes_j1=0;
        fautes_j2=0;
        let_j1=0;
        let_j2=0;
        manches_j1=0;
        manches_j2=0;
        current_manche=1;
        pts_manche1_j1=0;
        pts_manche1_j2=0;
        pts_manche2_j1=0;
        pts_manche2_j2=0;
        pts_manche3_j1=0;
        pts_manche3_j2=0;
        pts_j1=0;
        pts_j2=0;
        gagnant = "";

        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueur_service= joueur_service;
    }

    public Match(String joueur1, String joueur2, int pts_j1, int pts_j2,  int balle, int aces_j1, int aces_j2, int fautes_j1, int fautes_j2, int let_j1, int let_j2, int manches_j1, int manches_j2, String gagnant) {
        super();
        id= 0;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.balle = balle;
        this.aces_j1= aces_j1;
        this.aces_j2= aces_j2;
        this. fautes_j1= fautes_j1;
        this.fautes_j2= fautes_j2;
        this.let_j1= let_j1;
        this.let_j2= let_j2;
        this.manches_j1= manches_j1;
        this.manches_j2= manches_j2;
        this.pts_j1= pts_j1;
        this.pts_j2= pts_j2;
        this.gagnant= gagnant;
        pts_manche1_j1=0;
        pts_manche1_j2=0;
        pts_manche2_j1=0;
        pts_manche2_j2=0;
        pts_manche3_j1=0;
        pts_manche3_j2=0;
        joueur_service=1;
        current_manche = 1;

    }

    public void changementServeur()
    {
        if (joueur_service == 1)
        {
           joueur_service=2;
        }
        else if (joueur_service == 2)
        {
            joueur_service=1;
        }
    }

    public void PointsTotJ1(int point)
    {
        pts_j1= point;
    }

    public void PointsTotJ2 (int point)
    {
        pts_j2= point;
    }

    public boolean CheckWinner ()
    {
        boolean win = false;
        if (manches_j1 == 2)
        {
            gagnant= joueur1;
            win= true;

        }
        else if (manches_j2 == 2)
        {
            gagnant= joueur2;
            win = true;
        }
        return win;
    }

    public int CheckMancheAndWinner(){


            if (pts_j1 > 10 ) {
                switch (current_manche) {
                    case 1:
                        pts_manche1_j1= pts_j1;
                        manches_j1 += 1;
                        pts_manche1_j2= pts_j2;
                        pts_j1= 0;
                        pts_j2=0;

                        break;
                    case 2:
                        pts_manche2_j1= pts_j1;
                        manches_j1 += 1;
                        pts_manche2_j2= pts_j2;
                        pts_j1= 0;
                        pts_j2=0;
                        break;

                    case 3:
                        pts_manche3_j1= pts_j1;
                        manches_j1 += 1;
                        pts_manche3_j2= pts_j2;

                        break;

                }
                current_manche += 1;

            }
            else if (pts_j2 >10)
            {
                switch (current_manche) {
                    case 1:
                        pts_manche1_j1= pts_j1;
                        manches_j2 += 1;
                        pts_manche1_j2= pts_j2;
                        pts_j1= 0;
                        pts_j2=0;
                        break;
                    case 2:
                        pts_manche2_j1= pts_j1;
                        manches_j2 += 1;
                        pts_manche2_j2= pts_j2;
                        pts_j1= 0;
                        pts_j2=0;
                        break;
                    case 3:
                        pts_manche3_j1= pts_j1;
                        manches_j2 += 1;
                        pts_manche3_j2= pts_j2;

                        break;
                }
                current_manche += 1;
            }


        if (CheckWinner()== true)
        {
            return 1;
        }
        else {
            return 0;
        }


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getJoueur_service() {
        return joueur_service;
    }

    public void setJoueur_service(int joueur_service) {
        this.joueur_service = joueur_service;
    }

    public String getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(String joueur1) {
        this.joueur1 = joueur1;
    }

    public String getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(String joueur2) {
        this.joueur2 = joueur2;
    }

    public int getBalle() {
        return balle;
    }

    public void setBalle() {
        this.balle += 1;
    }

    public int getAces_j1() {
        return aces_j1;
    }

    public void setAces_j1() {
        this.aces_j1 +=1;
    }

    public int getAces_j2() {
        return aces_j2;
    }

    public void setAces_j2() {
        this.aces_j2 +=1;
    }

    public int getFautes_j1() {
        return fautes_j1;
    }

    public void setFautes_j1() {
        this.fautes_j1 +=1;
    }

    public int getFautes_j2() {
        return fautes_j2;
    }

    public void setFautes_j2() {
        this.fautes_j2 +=1;
    }

    public int getLet_j1() {
        return let_j1;
    }

    public void setLet_j1() {
        this.let_j1 +=1;
    }

    public int getLet_j2() {
        return let_j2;
    }

    public void setLet_j2() {
        this.let_j2 +=1;
    }

    public int getManches_j1() {
        return manches_j1;
    }

    public void setManches_j1() {
        this.manches_j1 +=1;
    }

    public int getManches_j2() {
        return manches_j2;
    }

    public void setManches_j2() {
        this.manches_j2 +=1;
    }

    public int getPts_manche1_j1() {
        return pts_manche1_j1;
    }

    public void setPts_manche1_j1() {
        this.pts_manche1_j1 +=1;
    }

    public int getPts_manche1_j2() {
        return pts_manche1_j2;
    }

    public void setPts_manche1_j2() {
        this.pts_manche1_j2 +=1;
    }

    public int getPts_manche2_j1() {
        return pts_manche2_j1;
    }

    public void setPts_manche2_j1() {
        this.pts_manche2_j1 +=1;
    }

    public int getPts_manche2_j2() {
        return pts_manche2_j2;
    }

    public void setPts_manche2_j2() {
        this.pts_manche2_j2 +=1;
    }

    public int getPts_manche3_j1() {
        return pts_manche3_j1;
    }

    public void setPts_manche3_j1() {
        this.pts_manche3_j1 += 1;
    }

    public int getPts_manche3_j2() {
        return pts_manche3_j2;
    }

    public void setPts_manche3_j2() {
        this.pts_manche3_j2 += 1;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getPts_j1() {
        return pts_j1;
    }

    public void setPts_j1() {
        this.pts_j1 +=1;
    }

    public int getPts_j2() {
        return pts_j2;
    }

    public void setPts_j2() {
        this.pts_j2 += 1;
    }

    public int getCurrent_manche() {
        return current_manche;
    }

    public void setCurrent_manche(int current_manche) {
        this.current_manche = current_manche;
    }

    public String getGagnant() {
        return gagnant;
    }

    public void setGagnant(String gagnant) {
        this.gagnant = gagnant;
    }

}
