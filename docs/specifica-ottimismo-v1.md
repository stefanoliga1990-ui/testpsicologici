# Aspettative sul futuro (ottimismo e pessimismo) — specifica v1.0

## Costrutto, popolazione e limiti

Questionario originale, informativo e non validato per adulti italiani. Esplora quanto alcune frasi descrivono **aspettative favorevoli** e **aspettative sfavorevoli** verso il proprio futuro in questa fase della vita. Ottimismo disposizionale indica una tendenza generale ad attendersi esiti favorevoli, non la certezza che accadranno. Le due aree sono lenti editoriali: nella letteratura sul LOT-R la struttura a uno o due fattori è dibattuta. La scelta di mantenerle distinte evita di annullare risposte favorevoli e sfavorevoli che possono coesistere; non dimostra che queste siano sottoscale valide dell'app.

Il questionario non misura speranza orientata a un obiettivo, autoefficacia, umore, salute mentale, realismo delle previsioni o probabilità di eventi. Circostanze economiche, salute, sicurezza e opportunità influenzano le aspettative. Non equipara pessimismo a disturbo né richiede positività obbligatoria. Non usa item o punteggi della LOT-R.

## Istruzioni, scala e restituzione

Riferimento: **la vita attuale e il futuro che la persona riesce a immaginare adesso**, senza finestra temporale artificiale. Istruzione: «Pensando al futuro dalla tua situazione attuale, quanto è vera per te ciascuna affermazione?». Scala di rappresentatività: `Per nulla vero per me=1`, `Poco vero per me=2`, `In parte vero per me=3`, `Molto vero per me=4`, `Del tutto vero per me=5`. Dieci item originali, cinque per area, tutti nello stesso verso **all'interno** dell'area; nessuna inversione o peso. Item intercalati; risultati nell'ordine aspettative favorevoli → sfavorevoli.

Si mostrano solo le due medie trasformate in barre grafiche 0–100, mai una media complessiva. Le soglie editoriali sono `LOW <2,5`, `MEDIUM 2,5–<3,5`, `HIGH ≥3,5`. Il motore `AREA_PROFILE` usa LOW se entrambe le aree sono basse, MIXED se nessuna è alta e almeno una è intermedia, FOCUSED se una sola è alta, BROAD se entrambe sono alte. **BROAD indica coesistenza di due tipi di aspettative, non molto ottimismo.** FOCUSED può indicare l'una o l'altra area: la scheda di area è necessaria per leggere quale. Nessun profilo classifica la persona o prevede esiti. Messaggio di supporto e sicurezza indipendente dalle fasce.

## Matrice area → indicatore → item originale → esclusione

| Area | Indicatore | Item | Esclusione |
|---|---|---|---|
| Favorevoli | Possibilità generali | Nel mio futuro vedo la possibilità di eventi favorevoli. | Non stima probabilità. |
| Sfavorevoli | Possibilità generali | Nel mio futuro mi aspetto eventi sfavorevoli. | Non giudica realismo. |
| Favorevoli | Cambiamenti | Di fronte ai cambiamenti che mi attendono, immagino anche sviluppi favorevoli. | Non presume controllo. |
| Sfavorevoli | Cambiamenti | Di fronte ai cambiamenti che mi attendono, immagino sviluppi sfavorevoli. | Non misura ansia. |
| Favorevoli | Imprevisti | Quando penso a situazioni future impreviste, considero possibili esiti favorevoli. | Non misura preparazione. |
| Sfavorevoli | Imprevisti | Quando penso a situazioni future impreviste, considero possibili esiti sfavorevoli. | Non misura prudenza. |
| Favorevoli | Opportunità | Mi aspetto di incontrare occasioni positive nel tempo che verrà. | Non presume opportunità uguali per tutti. |
| Sfavorevoli | Ostacoli | Mi aspetto di incontrare ostacoli nel tempo che verrà. | Non misura difficoltà effettive. |
| Favorevoli | Prospettiva complessiva | Guardando avanti, intravedo sviluppi che potrebbero andare bene per me. | Non certifica esito. |
| Sfavorevoli | Prospettiva complessiva | Guardando avanti, intravedo sviluppi che potrebbero andare male per me. | Non certifica esito. |

Le frasi restano candidate editoriali, non una traduzione o parafrasi sistematica della LOT-R. Il parallelismo fra alcune situazioni serve a rendere leggibile la coesistenza, ma potrebbe creare dipendenza fra risposte e va valutato in uno studio pilota.

## Matrice affermazione o scelta → fonte → evidenza → popolazione → limite

| Affermazione/scelta | Fonte | Evidenza | Popolazione | Limite |
|---|---|---|---|---|
| L'ottimismo disposizionale riguarda aspettative generalizzate sugli esiti futuri. | Scheier, Carver e Bridges (1994), [PMID 7815302](https://pubmed.ncbi.nlm.nih.gov/7815302/), DOI 10.1037//0022-3514.67.6.1063 | Revisione e studi della LOT-R | Campioni statunitensi | Non valida gli item, la scala o le soglie originali dell'app. |
| La struttura unidimensionale o bidimensionale è discussa; uno studio italiano sostiene due fattori. | Tommasi e Sartori (2024), *Counseling*, [DOI 10.14605/CS1722402](https://doi.org/10.14605/CS1722402) | CFA e validità concorrente della LOT-R | 161 universitari italiani, prevalentemente donne, 19–34 anni | Campione ristretto e strumento diverso; non stabilisce due fattori nell'app. |
| Le proprietà degli item della LOT-R sono state studiate in adulti italiani. | Steca, Monzani, Greco, Chiesi e Primi (2015), *Assessment*, [PMID 25078402](https://pubmed.ncbi.nlm.nih.gov/25078402/), DOI 10.1177/1073191114544471 | Analisi IRT e funzionamento differenziale per età e genere | 2.862 adulti italiani | Risultati specifici della LOT-R, non trasferibili agli item originali. |
| Un campione europeo di popolazione ha sostenuto la lettura separata di ottimismo e pessimismo. | Glaesmer e colleghi (2012), [PMID 22106985](https://pubmed.ncbi.nlm.nih.gov/22106985/) | Studio psicometrico della LOT-R | Popolazione tedesca | Norme e struttura della scala tedesca non sono norme italiane né prove per l'app. |

## Validazione ancora necessaria

Prima di chiamare lo strumento valido o affidabile occorrono revisione di esperti e utenti, interviste cognitive sugli item e sulla coesistenza delle aspettative, studio pilota, analisi fattoriali con risposte ordinali, affidabilità ed errore di misura per area, validità rispetto a misure esterne, equità e invarianza, e replica indipendente su adulti italiani. Le fonti orientano il costrutto; nessuna valida questo questionario.
