<#macro mainLayout title="Bienvenu sur la gestion des etudiants">
    <!doctype html>
    <html lang="fr">

    <head>
        <title>${title}</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
                crossorigin="anonymous"></script>
    </head>

    <body style="font-family: sans-serif">
    <div id="titre" class="container">
        <div class="row">
            <div class="col">
                <h2 class="text-center">Etudiants</h2>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <#nested/>
            </div>
        </div>
    </div>
    </body>
    </html>
</#macro>