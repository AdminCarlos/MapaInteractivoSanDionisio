package com.odesar.mapabioculturalinteractivosandionisio.Fragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.tabs.TabLayout
import com.odesar.mapabioculturalinteractivosandionisio.R
import kotlinx.android.synthetic.main.dialog_extra_info.view.*
import kotlin.math.roundToInt

class ExtraInfoDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0F)
        dialog?.window?.setGravity(GravityCompat.END)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        return inflater.inflate(R.layout.dialog_extra_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.txtCuerpoExtraInfo.text = """
                
                San Ramón, municipio siempre verde, de tierras fértiles e indígenas, llamado en sus orígenes San Antonio de Abay, “Abay o Abai”, nombre que en lengua Matagalpa significa “Estera de Piedra”, dado por sus primeros pobladores posiblemente por la petria solidez del terreno donde estaban asentados. Se encuentra localizado a 114 kms. de Managua capital de Nicaragua, y a 12 kms. de su cabecera Departamental Matagalpa. El paisaje de San Ramón lo conforman su hermoso conjunto urbano y su imponente paisaje natural.

                Su imagen urbana la constituyen sus alegres y cautivadores pobladores y sus bellos espacios físicos compuestos por Plazas y Parques; entre estos destaca la Plaza de la Identidad Histórica, convertida en un referente de la ciudad, la conforman diversas esculturas dedicadas a la mujer emprendedora, al adulto mayor, al agricultor, a los jóvenes que participaron en el servicio militar, los recursos naturales se representan a través de una hermosa cascada, la identidad indígena del municipio es representada con la escultura de un indio con su arco y flechas; en honor también a los aguerridos Indios Flecheros que de Yúcul,  Héroes Nacionales, que salieron a luchar en la Batalla de San Jacinto  para defender la soberanía del país ante la invasión de los filibusteros y en el centro de la Plaza se ubica la escultura del General Augusto C. Sandino.
                La Iglesia Centenaria es otro referente importante en la ciudad, edificada con materiales naturales y autóctonos de la localidad: adobe, paja, cal y ceniza, a esta la rodean el conjunto de viviendas pintorescas de arquitectura tradicional majestuosas montañas y la cultura viva en sus pobladores.
                El paisaje natural de San Ramón se distribuye entre 2 zonas climáticas muy representativas. 
                La Zona Húmeda al Norte del municipio, conformada por las Micro regiones La Pacayona y La Corona, con temperaturas entre 20°C y 22°C y alturas máximas desde 700 a 1300 msnm, por sus condiciones climáticas y productivas se asientan la mayoría de las haciendas cafetaleras.
                La Zona Seca al Sur del Municipio, la conforman las Microregiones: El Jícaro,  El Horno y Yúcul, presenta temperatura entre  24 °C  a 27 °C. En esta zona se desarrolla el cultivo de granos básicos, prductos no tradicionales y ganadería.
                La Zona Húmeda y Zona Seca del municipio poseen un capital natural lleno de exuberante flora tropical que adornan las comunidades, las plantaciones de café constituyen un paisaje natural del cultivo representativo de la región, que puede ser disfrutada a través de recorridos por senderos y caminos, y que desde lejos cubren y enmarcan el paisaje, así como las Milpas ancestrales. Los altos pinares de Tecunumannii que conforman la Reserva de Recursos Genéticos de Yúcul, el Área protegida de interés nacional comprendida entre las Serranías de Yúcul, Guabule y El Gorrión, y las riquezas naturales como el mariposario, ranario y orquideario como protagonistas del  Centro Turístico Rural Comunitario La Suana.

                De su exótica fauna silvestre se aprecian aves como chocoyos, loras, oropéndolas, guás, chachalaca, guis, pajuil (pavón), perico real, perico zapoyol, cobán, tucán, pilinche, guardabarranco, chorcha, sargento,  carpintero gorra roja y carpintero pinto. Se pueden observar los monos congos y arañas, oso perezoso, ocelotes, ardillas, venados, conejos, saínos, guardatinajes, guatusas, cusucu, mapachín, zorro cola pelada, zorro mión y zorro espin. Reptiles: rana ojos rojos, la culebra mica, boa, cera (o neya), chocoya, coral, barba amarilla, víbora de sangre, bejuca, tamagás, tortuga pequeña  de los ríos entre otras especies.

                
            """.trimIndent()

        view.menuTab.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {

                    0 -> {

                        view.txtCuerpoExtraInfo.text = """
                
                San Ramón, municipio siempre verde, de tierras fértiles e indígenas, llamado en sus orígenes San Antonio de Abay, “Abay o Abai”, nombre que en lengua Matagalpa significa “Estera de Piedra”, dado por sus primeros pobladores posiblemente por la petria solidez del terreno donde estaban asentados. Se encuentra localizado a 114 kms. de Managua capital de Nicaragua, y a 12 kms. de su cabecera Departamental Matagalpa. El paisaje de San Ramón lo conforman su hermoso conjunto urbano y su imponente paisaje natural.

                Su imagen urbana la constituyen sus alegres y cautivadores pobladores y sus bellos espacios físicos compuestos por Plazas y Parques; entre estos destaca la Plaza de la Identidad Histórica, convertida en un referente de la ciudad, la conforman diversas esculturas dedicadas a la mujer emprendedora, al adulto mayor, al agricultor, a los jóvenes que participaron en el servicio militar, los recursos naturales se representan a través de una hermosa cascada, la identidad indígena del municipio es representada con la escultura de un indio con su arco y flechas; en honor también a los aguerridos Indios Flecheros que de Yúcul,  Héroes Nacionales, que salieron a luchar en la Batalla de San Jacinto  para defender la soberanía del país ante la invasión de los filibusteros y en el centro de la Plaza se ubica la escultura del General Augusto C. Sandino.
                La Iglesia Centenaria es otro referente importante en la ciudad, edificada con materiales naturales y autóctonos de la localidad: adobe, paja, cal y ceniza, a esta la rodean el conjunto de viviendas pintorescas de arquitectura tradicional majestuosas montañas y la cultura viva en sus pobladores.
                El paisaje natural de San Ramón se distribuye entre 2 zonas climáticas muy representativas. 
                La Zona Húmeda al Norte del municipio, conformada por las Micro regiones La Pacayona y La Corona, con temperaturas entre 20°C y 22°C y alturas máximas desde 700 a 1300 msnm, por sus condiciones climáticas y productivas se asientan la mayoría de las haciendas cafetaleras.
                La Zona Seca al Sur del Municipio, la conforman las Microregiones: El Jícaro,  El Horno y Yúcul, presenta temperatura entre  24 °C  a 27 °C. En esta zona se desarrolla el cultivo de granos básicos, prductos no tradicionales y ganadería.
                La Zona Húmeda y Zona Seca del municipio poseen un capital natural lleno de exuberante flora tropical que adornan las comunidades, las plantaciones de café constituyen un paisaje natural del cultivo representativo de la región, que puede ser disfrutada a través de recorridos por senderos y caminos, y que desde lejos cubren y enmarcan el paisaje, así como las Milpas ancestrales. Los altos pinares de Tecunumannii que conforman la Reserva de Recursos Genéticos de Yúcul, el Área protegida de interés nacional comprendida entre las Serranías de Yúcul, Guabule y El Gorrión, y las riquezas naturales como el mariposario, ranario y orquideario como protagonistas del  Centro Turístico Rural Comunitario La Suana.

                De su exótica fauna silvestre se aprecian aves como chocoyos, loras, oropéndolas, guás, chachalaca, guis, pajuil (pavón), perico real, perico zapoyol, cobán, tucán, pilinche, guardabarranco, chorcha, sargento,  carpintero gorra roja y carpintero pinto. Se pueden observar los monos congos y arañas, oso perezoso, ocelotes, ardillas, venados, conejos, saínos, guardatinajes, guatusas, cusucu, mapachín, zorro cola pelada, zorro mión y zorro espin. Reptiles: rana ojos rojos, la culebra mica, boa, cera (o neya), chocoya, coral, barba amarilla, víbora de sangre, bejuca, tamagás, tortuga pequeña  de los ríos entre otras especies.

                
            """.trimIndent()

                    }

                    1 -> {

                        view.txtCuerpoExtraInfo.text = """
                            La economía del Municipio de San Ramón se fundamenta principalmente en la agricultura, en el cultivo de granos básicos y del café, como principales motores económicos del municipio, San Ramón es también impulsor de cultivos no tradicionales, tales como la Maracuyá, la malanga y otros productos, que se han convertido en alternativa de diversificación y mejoría económica para los pequeños y medianos productores del Municipio.
                            San Ramón proyecta su turismo local en sus diversas formas tanto vivencial como educativo, formando parte de los municipios de Nicaragua que conforman La Ruta del Café, cultivo que en San Ramón tiene una de sus cunas históricas y que caracteriza la cultura y la sociedad del municipio potencializando el turismo rural que, aunado a su gran belleza paisajística, hacen de San Ramón un lugar de encanto y gran atractivo turístico a nivel nacional e internacional.
                            De sus capitales bioculturales resaltan un sinnúmero de riquezas de manifestaciones en ámbitos tan diversos como la música, la danza, la gastronomía tradicional y juegos ancestrales así como una riqueza en personas que practican oficios ancestrales en estrecha relación con la cultura, medio ambiente e identidad, que reflejan la memoria colectiva, cosmovisión, forma de vivir y relacionarse, entre estos destacan: cestería, trabajo con bambú y palma, bisutería con semillas, trabajos en barro, músicos, carpintería, conocimiento en medicina natural, sobadores, parteras, rezadoras, costureras, zapateros, panadería artesanal, trabajos con adobe entre otros.

                        """.trimIndent()

                    }

                    2 -> {

                        view.txtCuerpoExtraInfo.text = """
                            Los sitios naturales y arqueológicos del municipio, hablan de la geografía sagrada por la que está constituido San Ramón, se pueden observar en el paisaje natural de la zona, formaciones rocosas, peñascos, montículos, petrograbados, cerros, saltos, quebradas y ríos que tienen un significado espiritual especial para la población y que forman parte del conjunto de formaciones geológicas del departamento de Matagalpa, y que utilizaron los primeros pobladores indígenas para delimitar el territorio, de estos elementos se pueden mencionar: Cerro El Toro, Cerro Azancor, Cerro El Esquirín, Cerro San Pablo, Cerro Los Castillos, Cerro El Espejo, Cerro El Coscuelo, Peña El Carmen, Piedra La Bailadora, Mina La Reina, Mina Verde, Montículos del Horno, Montículos del Trentino, Cañon de Guabule, Salto El Edén, Salto La Campana, Salto La Corona, Salto La Pita, Cañón de Guabule, Poza El Duende, Poza La Bruja, Rio Tapasle, Río Yasica, Rio Guabule, entre otros, que forman parte de la memoria oral del municipio y que genera en los pobladores un gran respeto por los mismos, así como su interés por su protección y preservación.
                            Las riquezas y hallazgos de recursos bioculturales  en el municipio de San Ramón lo hacen  dueño de un potencial turístico extraordinario, con la creación de corredores temáticos estratégicos se puede promover, recuperar y proteger  los sitios naturales y productos bioculturales, vinculados a la actividad alrededor del Café: Corredor Temático del Café; y el impulso y fomento de los sitios y productos bioculturales vinculados a los conocimientos ancestrales y talento humano: Corredor Temático El Camino de Abay.

                        """.trimIndent()

                    }

                    3 -> {

                        view.txtCuerpoExtraInfo.text = """
                            La memoria oral del municipio está conformada por un mundo en relación a sus personajes fantásticos o encantos que tienen estrecha vinculación con los sitios naturales de la zona, y ha sido transmitida en las comunidades por los abuelos y abuelas de generación en generación lo que ha permitido mantenerlos vivos en el tiempo, de estos personajes fantásticos se pueden escuchar historias y leyendas sobre el Sisimique, la Mocuana, los duendes que aparecen en los cerros y montañas, cerros que crecen, peñascos que bailan, apariciones en caminos, quebradas, pozas y ojos de agua.  
                            A San Ramón lo constituyen una riqueza en tradiciones que promueven la cohesión social y el vínculo territorial, entre estas tradiciones destacan: Celebración en honor a San Ramón Nonato desarrollada con actividades religiosas y populares como carreras de cinta, palo lucio, bailes, música de mazurca, ferias, certámenes artísticos, se realiza en el mes de Agosto celebrando también el aniversario de San Ramón de ser elevado a municipio y ciudad.
                            Otras tradiciones importantes que se celebran en el municipio son la Celebración de la Cosecha y la Vela de la Vara; realizada cada año por la comunidad indígena y su consejo de ancianos para escoger su autoridad tradicional, el Día de los difuntos y diversas peregrinaciones realizadas entre las comunidades del municipio.

                        """.trimIndent()

                    }

                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

        } )

    }

    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var width = Resources.getSystem().displayMetrics.widthPixels
        var height = Resources.getSystem().displayMetrics.heightPixels

        dialog!!.window!!.setLayout(
            (width * 1.0).roundToInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

}