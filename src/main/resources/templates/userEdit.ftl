<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container">
        <h2 class="mb-3 ml-5">Edit profile</h2>
    </div>

    <form action="/userSave" method="post">
        <div class="container">
            <div class="input-group input-group-lg mb-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg" style="width: 12rem;">username</span>
                </div>
                <input type="text" value="${user.username}" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-lg">
            </div>
        </div>

        <div class="container">
            <div class="input-group input-group-lg mb-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg" style="width: 12rem;">first_name</span>
                </div>
                <input type="text" value="${user.first_name}" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-lg">
            </div>
        </div>

        <div class="container">
            <div class="input-group input-group-lg mb-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg" style="width: 12rem;">last_name</span>
                </div>
                <input type="text" value="${user.last_name}" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-lg">
            </div>
        </div>

        <div class="container">
            <div class="input-group input-group-lg mb-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg" style="width: 12rem;">second_name</span>
                </div>
                <input type="text" value="${user.second_name}" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-lg">
            </div>
        </div>

        <div class="container">
            <div class="input-group input-group-lg mb-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg" style="width: 12rem;">email</span>
                </div>
                <input type="text" value="${user.email}" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-lg">
            </div>
        </div>

        <div class="container">
            <div class="input-group input-group-lg mb-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg" style="width: 12rem;">password</span>
                </div>
                <input type="text" value="${user.password}" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-lg">
            </div>
        </div>

        <div class="container">
            <div class="input-group input-group-lg mb-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg" style="width: 12rem;">snils</span>
                </div>
                <input type="text" value="${user.snils}" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-lg">
            </div>
        </div>

        <div class="container">
            <div class="row justify-content-md-center mt-5">
                <div class="col col-lg-2">
                    <button type="submit" class="btn btn-success mr-5" style="width: 8rem;">Save</button>
                </div>
                <div class="col-md-auto">
                    <a class="btn btn-outline-primary" href="/" style="width: 8rem;">Cancel</a>
                </div>
            </div>
        </div>

    </form>
</@c.page>
