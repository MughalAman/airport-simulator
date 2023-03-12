package simu.model;

/**
 * Tämä luokka määrittelee tapahtumien tyypit simulointimallin vaatimusten perusteella.
 * @author
 * @version
 */
public enum TapahtumanTyyppi {
    ENTRANCE, // Lentoaseman sisäänkäynti
    INFO, // Lentoaseman tiedotus
    CHECKINAUTO, // Automatisoitu sisäänkirjautuminen
    CHECKINMANUAL, // Manuaalinen sisäänkirjautuminen
    SECURITY, // Turvatarkastus
    SECURITYGATE, // Turvaportti
    GATE, // Portti
    PLANE // Lentokone
}
