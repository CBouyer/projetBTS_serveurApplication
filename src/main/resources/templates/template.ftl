<#macro mainLayout title="Bienvenu sur la gestion des etudiants">
    <!doctype html>
    <html lang="fr">

<head>
    <title>${title}</title>
    <meta charset="utf-8">
    <!-- Bootstrap 5.1.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script async src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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