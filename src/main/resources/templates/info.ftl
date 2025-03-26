<#import "./template.ftl" as layout />
<@layout.mainLayout title="Gestion Bénévole">


    <input id="freemarkervar" type="hidden" value="${etudiant.id}"/>

    <div class="modal-dialog modal-dialog-centered" id="modalConfirmer" tabindex="-1"
         aria-labelledby="modalConfirmationLabel" aria-hidden="true">
        <div class="modal-dialog  modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalConfirmationLabel">Informations</h5>
                    <button type="button" class="btn-close" onclick="btnAnnuler()" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <b>Nom :${etudiant.firstname}</b>
                    <b>Prénom: ${etudiant.lastname}</b>
                    <p>Date de naissance:${etudiant.age}</b>
                        <img src="${etudiant.image}" alt="pas d'image" height="250" width="auto"></p>



                </div>
                <div class="modal-footer">
                    <button type="button" id="btnAnnuler" onclick="btnAnnuler()" class="btn btn-secondary"
                            data-bs-dismiss="modal"><b>Fermer</b></button>
                </div>
            </div>
        </div>
    </div>


    <script>
        function btnAnnuler() {
            var val = document.getElementById("freemarkervar").value;
            document.location.href = "/" ;
        }
    </script>

</@layout.mainLayout>