package net.bassmann.dass.year2020;

public class Year2020Day01 {

  /**
   * <div class="wrapper svelte-ysyhwm">
   *
   * <h1 class="svelte-ysyhwm">Regler</h1>
   *
   * <ul>
   *   <li>Det er ikke tillatt å tilegne seg tilgang til andre brukeres konto.
   *   <li>Det er ikke tillatt å angripe/bruteforce APIer på <code class="svelte-ysyhwm">
   *       dass.npst.no</code>.
   *   <li>Det er ikke tillatt å bruke støtende alias.
   *   <li>Det er lov å samarbeide.
   *   <li>Vi oppfordrer sterkt til å ikke publisere løsningsforlag åpent før tidligst 25. desember.
   * </ul>
   *
   * <p>Ved brudd på reglene forbeholder vi oss retten til å endre alias, blokkere og/eller fjerne
   * brukere uten forvarsel.
   *
   * <h1 class="svelte-ysyhwm">Generell informasjon</h1>
   *
   * <p>Poenggivende flagg er på formen <code class="svelte-ysyhwm">PST{.*}</code>, eksempelvis
   * <code class="svelte-ysyhwm">PST{littTekstOgTall}</code>.
   *
   * <p>Flagg leveres i <code class="svelte-ysyhwm">Snabel-A</code> som svar til
   * <code class="svelte-ysyhwm">Mellomleder</code> på dagens oppgave.
   *
   * <p>Ved å oppgi en gyldig epostadresse får du muligheten til å vinne premier. Dette er følgelig
   * helt frivillig! Epostadresser vil kun bli benyttet til å kontakte eventuelle vinnere, og de vil
   * bli slettet etter at alle vinnere har blitt utropt. Du kan når som helst endre eposten din
   * under <code class="svelte-ysyhwm">Instillinger</code> i applikasjonen
   * <code class="svelte-ysyhwm">Snabel-A</code>.
   *
   * <p>Kun brukere som har oppgitt en gyldig epost kan vinne premier. Vinneren av julekalenderen
   * vil bli tilfeldig trukket ut blant alle deltakerne, jo flere poeng du har, jo større vil
   * sansynligheten for å bli trukket ut være.
   *
   * <p>Alle brukerkontoer og tilhørende data vil bli slettet etter at alle vinnere har blitt
   * utropt, senest 1. februar 2021.
   *
   * <h1 class="svelte-ysyhwm">Samarbeid</h1>
   *
   * <p>Det finnes en uoffisiell <a href="https://discord.gg/8kvF3aU" target="_blank" rel="noopener
   * noreferrer">Discord-kanal</a> som i fjor ble flittig brukt av mange deltagere. Vi oppfordrer
   * alle som ønsker å diskutere oppgavene med andre om å benytte seg av denne. PST har ingen ting
   * med kanalen å gjøre, men har fått lov til å ha en bruker tilstede som man kan stille spørsmål
   * til dersom man ønsker det. Vennligst respekter reglene administratorene av kanalen har satt.
   * </div>
   */

  /**
   * Velkommen til NPSTs julekalender 2020 Velkommen til NPSTs julekalender 2020! 🎉
   *
   * <p>Vi ønsker deg også velkommen til vårt splitter nye Digitale Arkiv- og SaksbehandlingsSystem.
   * I 2017 engasjerte NPST konsulentselskapet Winther Consulting til å digitalisere vårt
   * eksisterende saksbehandlingssystem, og vi er så heldig å lansere dette i dag, for alle ansatte
   * og til dere: våre nye alvebetjenter.
   *
   * <p>Underveis i testingen av applikasjonen har samtlige av testgruppene ytret at løsningen er
   * "en tro kopi av dagens eksisterende løsning, dog på web". Dette er vi i ledergruppa veldig
   * fornøyd med, da vi mener at overgangen fra gammelt system til nytt vil gå så sømløst som mulig!
   *
   * <p>Praktisk info
   *
   * <p>Du vil få arbeidsoppgaver hver dag, og første arbeidsoppgave kommer i morgen kl 07.00. Pass
   * også på å sette deg godt inn i reglene (start -> kjør... -> informasjon.exe).
   *
   * <p>Det finnes en uoffisiell Discord-kanal som i fjor ble flittig brukt av mange deltagere. Vi
   * oppfordrer alle som ønsker å diskutere oppgavene med andre om å benytte seg av denne. Hverken
   * NPST eller vår sammarbeidsparner PST har noe med kanalen å gjøre, men har fått lov til å ha en
   * bruker tilstede som man kan stille spørsmål til dersom man ønsker det. Vennligst respekter
   * reglene administratorene av kanalen har satt.
   *
   * <p>Igjen, velkommen til oss!
   *
   * <p>🎅
   */

  /**
   * Hei,
   *
   * <p>Kan du bekrefte at du har fått tilgang til systemet? Det gjør du ved å svare på denne
   * meldingen med verifiseringskoden RUV{JgkJqPåGtFgvLwnKilgp}.
   *
   * <p>OBS: Jeg mistet verifiseringskoden din i salaten, så mulig du må rette opp i den før du
   * svarer.
   *
   * <p>Vennlig hilsen din nærmeste leder
   */
  public static void main(String[] args) {
    final String input = "RUV{JgkJqPåGtFgvLwnKilgp}";
    final String realResult = "PST{HeiHoNåErDetJulIgjen}";

    String result =
        input
            .codePoints()
            .map(cp -> isLetter(cp) ? cp - 2 : cp)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

    System.out.println(result);
  }

  static boolean isLetter(int codePoint) {
    return 'A' <= codePoint && codePoint <= 'Z' || 'a' <= codePoint && codePoint <= 'z';
  }
}
