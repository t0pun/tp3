package org.example.tp3b;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
public class LoginClass {
    // Déclaration du logger au niveau de la classe
    private static final Logger logger = LoggerFactory.getLogger(LoginClass.class);

    public static void main(String[] args) throws Exception {
        logger.info("Méthode main exécutée.");;
        logger.info("Méthode main exécutée.");
        System.out.println("Début de l'exécution...");
        // Lance le processus Spoon
        Launcher launcher = new Launcher();
        launcher.addInputResource("src/main/java");// Répertoire de ton code source

        launcher.getEnvironment().setAutoImports(true);// Ajouter les imports nécessaires

        Factory factory = launcher.getFactory();
        // Lance l'analyse et la transformation du code
        CtModel model = launcher.buildModel();
        System.out.println("Modèle Spoon construit.");
        // Parcours toutes les classes du modèle
        for (CtType<?> ctClass : factory.Class().getAll()) {
            System.out.println("Analyse de la classe : " + ctClass.getSimpleName());
            addLoggerToClass(((CtClass<?>) (ctClass)));// Ajoute le logger à la classe

            addLoggingToMethods(((CtClass<?>) (ctClass)));// Ajoute les logs à chaque méthode

        }
        // Enregistre les fichiers modifiés
        launcher.prettyprint();
        System.out.println("Fin de l'exécution et fichiers générés.");
    }

    public static void addLoggerToClass(CtClass<?> ctClass) {
        logger.info("Méthode addLoggerToClass exécutée.");;
        logger.info("Méthode addLoggerToClass exécutée.");
        System.out.println("Ajout du logger dans la classe : " + ctClass.getSimpleName());
        // Vérifie si un logger existe déjà dans la classe
        boolean loggerExists = ctClass.getFields().stream().anyMatch(field -> field.getSimpleName().equals("logger"));
        if (loggerExists) {
            System.out.println("Logger déjà présent dans la classe : " + ctClass.getSimpleName());
            return;
        }
        // Crée un champ logger
        CtField<?> loggerField = ctClass.getFactory().Field().create(ctClass, Set.of(ModifierKind.PRIVATE, ModifierKind.STATIC, ModifierKind.FINAL), ctClass.getFactory().Type().createReference(Logger.class), "logger", ctClass.getFactory().Code().createCodeSnippetExpression(("LoggerFactory.getLogger(" + ctClass.getSimpleName()) + ".class)"));
        // Ajoute le logger au début de la classe
        List<CtField<?>> existingFields = new ArrayList<>(ctClass.getFields());
        if (!existingFields.isEmpty()) {
            ctClass.removeField(existingFields.get(0));// Supprime temporairement le premier champ s'il existe

        }
        ctClass.addFieldAtTop(loggerField);// Ajoute le logger en haut

        existingFields.forEach(ctClass::addField);// Réinsère les champs existants après le logger

        // Ajoute les types nécessaires à la fabrique pour gérer les imports
        Factory factory = ctClass.getFactory();
        factory.Type().createReference(Logger.class);// Référence pour Logger

        factory.Type().createReference(LoggerFactory.class);// Référence pour LoggerFactory

        System.out.println("Logger ajouté en début de la classe avec les imports.");
    }

    public static void addLoggingToMethods(CtClass<?> ctClass) {
        logger.info("Méthode addLoggingToMethods exécutée.");;
        logger.info("Méthode addLoggingToMethods exécutée.");
        System.out.println("Ajout des logs dans les méthodes de la classe : " + ctClass.getSimpleName());
        // Parcours toutes les méthodes de la classe
        for (CtMethod<?> method : ctClass.getMethods()) {
            System.out.println("Ajout du log à la méthode : " + method.getSimpleName());
            addLogBeforeMethodExecution(method);
        }
    }

    public static void addLogBeforeMethodExecution(CtMethod<?> method) {
        logger.info("Méthode addLogBeforeMethodExecution exécutée.");;
        logger.info("Méthode addLogBeforeMethodExecution exécutée.");
        // Crée un log avant la méthode
        String logMessage = ("Méthode " + method.getSimpleName()) + " exécutée.";
        // Crée une instruction de log
        CtStatement logStatement = method.getFactory().Code().createCodeSnippetStatement(("logger.info(\"" + logMessage) + "\");");
        // Ajoute l'instruction de log au début de la méthode
        CtBlock<?> body = method.getBody();
        if (body != null) {
            body.insertBegin(logStatement);
            System.out.println("Log ajouté à la méthode : " + method.getSimpleName());
        } else {
            System.out.println(("Méthode " + method.getSimpleName()) + " sans corps (pas de log ajouté).");
        }
    }
}