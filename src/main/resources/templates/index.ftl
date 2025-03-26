<#import "template.ftl" as layout />
<@layout.mainLayout>

    <div class="row">
        <div class="col">
            <table class="table table table-bordered table-striped text-center">
                <thead class="table-dark" >
                <tr>
                    <#--                    <th scope="col">Id</th>-->
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Age</th>
                    <th scope="col">Url image</th>
                    <th scope="col">Image</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list etudiants as etu>
                    <tr>
                        <#--                        <td class="align-middle">${etu.id}</td>-->
                        <td class="align-middle">${etu.firstname}</td>
                        <td class="align-middle">${etu.lastname}</td>
                        <td class="align-middle">${etu.age}</td>
                        <td class="align-middle">${etu.image}</td>
                        <td class="align-middle"><img src="${etu.image}" alt="pas d'image" height="100"
                                                      width="auto">
                        </td>
                        <td class="align-middle">
                            <a href="/info/${etu.id}" class="btn btn-danger float-right mr-3"
                               role="button"><b>info</b></a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

</@layout.mainLayout>